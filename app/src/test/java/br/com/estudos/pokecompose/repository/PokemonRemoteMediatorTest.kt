package br.com.estudos.pokecompose.repository

import androidx.paging.*
import br.com.estudos.pokecompose.data.dataBase.local.PokemonDao
import br.com.estudos.pokecompose.data.dataBase.local.PokemonDetailDao
import br.com.estudos.pokecompose.data.dataBase.local.PokemonRemoteKeyDao
import br.com.estudos.pokecompose.data.dataBase.remote.PokemonService
import br.com.estudos.pokecompose.data.model.local.PokemonAndDetail
import br.com.estudos.pokecompose.data.model.remote.ListPokemonRemote
import br.com.estudos.pokecompose.data.model.remote.PokemonDetailRemote
import br.com.estudos.pokecompose.data.repository.PokemonRemoteMediator
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test

@ExperimentalPagingApi
internal class PokemonRemoteMediatorTest {

    private val pokemonDaoMock = mockk<PokemonDao>()
    private val pokemonDetailDaoMock = mockk<PokemonDetailDao>()
    private val pokemonRemoteKeyDaoMock = mockk<PokemonRemoteKeyDao>()
    private val pokemonServiceMock = mockk<PokemonService>()
    private var response = mockk<ListPokemonRemote>()
    private val pokemonDetailMock = mockk<PokemonDetailRemote>(relaxed = true)
    private val remoteMediatorSpy = spyk(
        PokemonRemoteMediator(
            pokemonDao = pokemonDaoMock,
            pokemonRemoteKeyDao = pokemonRemoteKeyDaoMock,
            pokemonService = pokemonServiceMock,
            pokemonDetailDao = pokemonDetailDaoMock
        )
    )

    @After
    fun after() {
        response = mockk()
    }

    @Test
    fun `refresh Load Returns SuccessResult When More Data Is Present()`() {

        coEvery { response.results } answers { mockk(relaxed = true) }
        coEvery { response.previous } answers { null }
        coEvery { response.next } answers { "https://pokeapi.co/api/v2/pokemon?offset=10&limit=10" }
        coEvery { remoteMediatorSpy.getOffsetParameter(any()) } returns 1

        coEvery { pokemonServiceMock.getListPokemon(limit = 100, offset = 0) } answers { response }
        coEvery { pokemonServiceMock.getPokemonDetails(any()) } answers { pokemonDetailMock }

        coEvery { pokemonDaoMock.saveAll(any()) } answers {}
        coEvery { pokemonRemoteKeyDaoMock.saveAll(any()) } answers {}
        coEvery { pokemonDetailDaoMock.saveAll(any()) } answers {}

        val result = runBlocking {
            remoteMediatorSpy.load(LoadType.REFRESH, getPagingState())
        }

        assert(result is RemoteMediator.MediatorResult.Success)
        assert((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached.not())
    }

    @Test
    fun `refresh Load Success And End OfPagination When No More Data`() {

        coEvery { response.results } answers { listOf() }
        coEvery { response.previous } answers { null }
        coEvery { response.next } answers { null }
        coEvery { remoteMediatorSpy.getOffsetParameter(any()) } returns null

        coEvery { pokemonServiceMock.getListPokemon(limit = 100, offset = 0) } answers { response }

        coEvery { pokemonDetailDaoMock.saveAll(any()) } answers {}
        coEvery { pokemonDaoMock.saveAll(any()) } answers {}
        coEvery { pokemonRemoteKeyDaoMock.saveAll(any()) } answers {}

        val result = runBlocking {
            remoteMediatorSpy.load(LoadType.REFRESH, getPagingState())
        }

        assert(result is RemoteMediator.MediatorResult.Success)
        assert((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }

    @Test
    fun `refresh Load Returns Error Result When Error Occurs`() {

        coEvery { pokemonServiceMock.getListPokemon(limit = 100, offset = 0) }.throws(
            RuntimeException()
        )

        val result = runBlocking {
            remoteMediatorSpy.load(LoadType.REFRESH, getPagingState())
        }

        assert(result is RemoteMediator.MediatorResult.Error)
    }

    private fun getPagingState() = PagingState<Int, PokemonAndDetail>(
        pages = listOf(),
        anchorPosition = null,
        config = PagingConfig(10),
        leadingPlaceholderCount = 10
    )
}
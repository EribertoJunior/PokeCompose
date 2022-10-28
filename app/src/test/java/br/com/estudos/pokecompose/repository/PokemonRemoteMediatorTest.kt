package br.com.estudos.pokecompose.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import br.com.estudos.pokecompose.model.api.ListPokemonApi
import br.com.estudos.pokecompose.model.api.PokemonDetailRemote
import br.com.estudos.pokecompose.model.local.Pokemon
import br.com.estudos.pokecompose.repository.local.PokemonDao
import br.com.estudos.pokecompose.repository.local.PokemonRemoteKeyDao
import br.com.estudos.pokecompose.repository.remote.PokemonService
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test

@ExperimentalPagingApi
internal class PokemonRemoteMediatorTest {

    private val pokemonDaoMock = mockk<PokemonDao>()
    private val pokemonRemoteKeyDaoMock = mockk<PokemonRemoteKeyDao>()
    private val pokemonServiceMock = mockk<PokemonService>()
    private var response = mockk<ListPokemonApi>()
    private val pokemonDetailMock = mockk<PokemonDetailRemote>(relaxed = true)
    private val remoteMediatorSpy = spyk(
        PokemonRemoteMediator(
            pokemonDao = pokemonDaoMock,
            pokemonRemoteKeyDao = pokemonRemoteKeyDaoMock,
            pokemonService = pokemonServiceMock
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

    private fun getPagingState() = PagingState<Int, Pokemon>(
        pages = listOf(),
        anchorPosition = null,
        config = PagingConfig(10),
        leadingPlaceholderCount = 10
    )
}
package br.com.estudos.pokecompose.data.dataSource.remote

import br.com.estudos.pokecompose.data.dataBase.remote.PokemonService
import br.com.estudos.pokecompose.data.model.remote.EvolutionChainRemote
import br.com.estudos.pokecompose.data.model.remote.ListPokemonRemote
import br.com.estudos.pokecompose.data.model.remote.PokemonDetailRemote
import br.com.estudos.pokecompose.data.model.remote.SpeciesRemote
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class RemoteDataSourceImplTest {

    lateinit var remoteDataSourceImplMock: RemoteDataSourceImpl
    lateinit var pokemonService: PokemonService

    @Before
    fun setUp() {
        pokemonService = mockk()
        remoteDataSourceImplMock = spyk(RemoteDataSourceImpl(pokemonService))
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `deve retornar ListPokemonRemote quando PokemonService retornar um ListPokemonRemote`() {

        val listPokemonRemote = mockk<ListPokemonRemote>(relaxed = true)

        coEvery { pokemonService.getListPokemon(any(), any()) } answers { listPokemonRemote }

        runBlocking {
            val listPokemon = remoteDataSourceImplMock.getListPokemon(0, 0)
            assertEquals(listPokemonRemote, listPokemon)
        }
    }

    @Test
    fun `deve retornar PokemonDetailRemote quando PokemonService retornar um PokemonDetailRemote`() {

        val pokemonDetailRemote = mockk<PokemonDetailRemote>(relaxed = true)

        coEvery { pokemonService.getPokemonDetails(any()) } answers { pokemonDetailRemote }

        runBlocking {
            val pokemonDetails = remoteDataSourceImplMock.getPokemonDetails("")
            assertEquals(pokemonDetailRemote, pokemonDetails)
        }
    }

    @Test
    fun `deve retornar SpeciesRemote quando PokemonService retornar um SpeciesRemote`() {

        val speciesRemote = mockk<SpeciesRemote>(relaxed = true)

        coEvery { pokemonService.searchPokemonSpecie(any()) } answers { speciesRemote }

        runBlocking {
            val pokemonDetails = remoteDataSourceImplMock.searchPokemonSpecie("")
            assertEquals(speciesRemote, pokemonDetails)
        }
    }

    @Test
    fun `deve retornar null quando PokemonService nao retornar um SpeciesRemote`() {

        coEvery { pokemonService.searchPokemonSpecie(any()) } answers { null }

        runBlocking {
            val pokemonDetails = remoteDataSourceImplMock.searchPokemonSpecie("")
            assertEquals(null, pokemonDetails)
        }
    }

    @Test
    fun `deve retornar EvolutionChainRemote quando PokemonService retornar um EvolutionChainRemote`() {

        val evolutionChainRemote = mockk<EvolutionChainRemote>(relaxed = true)

        coEvery { pokemonService.searchEvolutionChan(any()) } answers { evolutionChainRemote }

        runBlocking {
            val actual = remoteDataSourceImplMock.searchEvolutionChan("")
            assertEquals(evolutionChainRemote, actual)
        }
    }

}
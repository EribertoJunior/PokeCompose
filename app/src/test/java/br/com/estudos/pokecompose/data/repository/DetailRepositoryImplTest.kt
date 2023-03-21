package br.com.estudos.pokecompose.data.repository

import br.com.estudos.pokecompose.data.dataSource.local.LocalDataSource
import br.com.estudos.pokecompose.samples.listPokemonSample
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class DetailRepositoryImplTest {

    private lateinit var localDataSource: LocalDataSource
    private lateinit var detailRepositoryImpl: DetailRepositoryImpl

    @Before
    fun setUp() {
        localDataSource = mockk()
        detailRepositoryImpl = spyk(DetailRepositoryImpl(localDataSource))
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `should return a PokemonAndDetail when localDataSource returns a PokemonAndDetail`() {
        val pokemonAndDetail = listPokemonSample[0]

        every { localDataSource.searchPokemonByName(any()) } answers {
            flow {
                emit(pokemonAndDetail)
            }
        }

        runBlocking {
            val searchPokemonByName = detailRepositoryImpl.searchPokemonByName("")

            assertEquals(pokemonAndDetail, searchPokemonByName.first())
        }
    }

    @Test
    fun `should return NoSuchElementException when a PokemonAndDetail from localDataSource is not returned`() {
        every { localDataSource.searchPokemonByName(any()) } answers {
            flow {}
        }
        assertThrows(
            "Expected at least one element",
            NoSuchElementException::class.java
        ) {
            runBlocking {
                val searchPokemonByName = detailRepositoryImpl.searchPokemonByName("")
                searchPokemonByName.first()
            }
        }
    }

}
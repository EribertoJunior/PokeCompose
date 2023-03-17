package br.com.estudos.pokecompose.data.dataBase.local.converters

import br.com.estudos.pokecompose.data.dataBase.local.entities.PokemonDetailStats
import br.com.estudos.pokecompose.data.dataBase.local.entities.Stat
import io.mockk.spyk
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ConverterPokemonDetailStatsTest {

    private var converterPokemonDetailStats = spyk(ConverterPokemonDetailStats())

    @Test
    fun `deve retornar um jsonArray quando uma lista de PokemonDetailStats for passada`() {

        val jsonArray = "[{\"baseStat\":0,\"effort\":0,\"stat\":{\"name\":\"stat\",\"url\":\"/321\"}}]"

        run {
            val listString = converterPokemonDetailStats.saveListToListString(
                listOf(
                    PokemonDetailStats(baseStat = 0, effort = 0, Stat(name = "stat", url = "/321"))
                )
            )
            assertEquals(jsonArray, listString)
        }
    }

    @Test
    fun `deve retornar uma lista de PokemonDetailStats quando um jsonArray for passada`() {

        val jsonArray = "[{\"baseStat\":0,\"effort\":0,\"stat\":{\"name\":\"stat\",\"url\":\"/321\"}}]"

        val listOf = listOf(
            PokemonDetailStats(baseStat = 0, effort = 0, Stat(name = "stat", url = "/321"))
        )

        run {
            val list = converterPokemonDetailStats.restoreList(jsonArray)
            assertEquals(listOf, list)
        }
    }
}
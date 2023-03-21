package br.com.estudos.pokecompose.data.dataBase.local.converters

import br.com.estudos.pokecompose.data.dataBase.local.entities.PokemonDetailStats
import br.com.estudos.pokecompose.data.dataBase.local.entities.Stat
import io.mockk.spyk
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ConverterPokemonDetailStatsTest {

    private var converterPokemonDetailStats = spyk(ConverterPokemonDetailStats())

    @Test
    fun `should return a jsonArray when a list of Pokemon Detailed Stats is passed`() {

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
    fun `should return a list of PokemonDetailStats when a jsonArray is passed`() {

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
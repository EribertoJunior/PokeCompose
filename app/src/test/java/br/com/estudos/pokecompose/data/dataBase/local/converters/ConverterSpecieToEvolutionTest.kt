package br.com.estudos.pokecompose.data.dataBase.local.converters

import br.com.estudos.pokecompose.data.dataBase.local.entities.SpecieToEvolution
import io.mockk.spyk
import org.junit.Assert.assertEquals
import org.junit.Test

class ConverterSpecieToEvolutionTest {

    private var converterSpecieToEvolution = spyk(ConverterSpecieToEvolution())

    @Test
    fun `should return a jsonArray when a SpecieToEvolution list is passed`() {

        val jsonArray = "[{\"name\":\"name\",\"imageUrl\":\"/321\"}]"
        val listOf = listOf(
            SpecieToEvolution(name = "name", imageUrl = "/321")
        )

        run {
            val listString = converterSpecieToEvolution.saveListToListString(listOf)
            assertEquals(jsonArray, listString)
        }
    }

    @Test
    fun `should return a list of SpecieToEvolution when a jsonArray is passed`() {

        val jsonArray = "[{\"name\":\"name\",\"imageUrl\":\"/321\"}]"

        val listOf = listOf(
            SpecieToEvolution(name = "name", imageUrl = "/321")
        )

        run {
            val list = converterSpecieToEvolution.restoreList(jsonArray)
            assertEquals(listOf, list)
        }
    }
}
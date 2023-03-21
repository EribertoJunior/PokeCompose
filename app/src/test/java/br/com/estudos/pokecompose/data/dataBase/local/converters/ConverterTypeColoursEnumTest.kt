package br.com.estudos.pokecompose.data.dataBase.local.converters

import br.com.estudos.pokecompose.data.model.local.enums.TypeColoursEnum
import io.mockk.spyk
import org.junit.Assert.assertEquals
import org.junit.Test

class ConverterTypeColoursEnumTest {

    private var converterTypeColoursEnum = spyk(ConverterTypeColoursEnum())

    @Test
    fun `should return a jsonArray when a list of TypeColoursEnum is passed`() {

        val jsonArray = "[\"DRAGON\",\"FIRE\"]"
        val listOf = listOf(
            TypeColoursEnum.DRAGON,
            TypeColoursEnum.FIRE
        )

        run {
            val listString = converterTypeColoursEnum.saveListEnumToListString(listOf)
            assertEquals(jsonArray, listString)
        }
    }

    @Test
    fun `should return a list of TypeColoursEnum when a jsonArray is passed`() {

        val jsonArray = "[\"DRAGON\",\"FIRE\"]"

        val listOf = listOf(
            TypeColoursEnum.DRAGON,
            TypeColoursEnum.FIRE,
        )

        run {
            val list = converterTypeColoursEnum.restoreListEnum(jsonArray)
            assertEquals(listOf, list)
        }
    }
}
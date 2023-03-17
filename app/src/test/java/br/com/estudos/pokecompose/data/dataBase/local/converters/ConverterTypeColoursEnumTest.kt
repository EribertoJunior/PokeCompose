package br.com.estudos.pokecompose.data.dataBase.local.converters

import br.com.estudos.pokecompose.data.dataBase.local.entities.SpecieToEvolution
import br.com.estudos.pokecompose.data.model.local.enums.TypeColoursEnum
import io.mockk.spyk
import org.junit.Assert.*
import org.junit.Test

class ConverterTypeColoursEnumTest {

    private var converterTypeColoursEnum = spyk(ConverterTypeColoursEnum())

    @Test
    fun `deve retornar um jsonArray quando uma lista de TypeColoursEnum for passada`() {

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
    fun `deve retornar uma lista de TypeColoursEnum quando um jsonArray for passada`() {

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
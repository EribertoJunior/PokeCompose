package br.com.estudos.pokecompose.ui.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import br.com.estudos.pokecompose.data.dataBase.local.entities.*
import br.com.estudos.pokecompose.data.model.local.PokemonAndDetail
import br.com.estudos.pokecompose.data.model.local.enums.TypeColoursEnum
import org.junit.Rule
import org.junit.Test

class PokemonItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val pokemonAndDetail get() = PokemonAndDetail(
        pokemon = Pokemon(
            pokemonId = 10,
            name = "Teste",
            imageUrl = ""
        ),
        pokemonDetail = PokemonDetail(
            colorTypeList = listOf(
                TypeColoursEnum.DRAGON,
                TypeColoursEnum.FIGHTING
            ),
            sprites = Sprites(
                Other(
                    officialArtwork = OfficialArtwork(""),
                    home = Home("")
                )
            ),
            weight = 238,
            height = 13,
            stats = emptyList(),
            species = PokemonDetailSpecies(
                name = "",
                url = ""
            )
        ),
    )

    @Test
    fun pokemonItemTest_pokemonImage() {
        composeTestRule.setContent {
            PokemonItem(
                pokemonAndDetail = pokemonAndDetail
            )
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("pokemonItemTest")

        composeTestRule
            .onNodeWithContentDescription("Teste pokemon image")
            .assertExists()
    }

    @Test
    fun pokemonItemTest_pokemonId() {
        composeTestRule.setContent {
            PokemonItem(
                pokemonAndDetail = pokemonAndDetail
            )
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("pokemonItemTest")

        composeTestRule
            .onNodeWithText("#010")
            .assertExists()
    }

    @Test
    fun pokemonItemTest_pokemonName() {
        composeTestRule.setContent {
            PokemonItem(
                pokemonAndDetail = pokemonAndDetail
            )
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("pokemonItemTest")

        composeTestRule
            .onNodeWithText("Teste")
            .assertExists()
    }

    @Test
    fun pokemonItemTest_pokemonImageType() {
        composeTestRule.setContent {
            PokemonItem(
                pokemonAndDetail = pokemonAndDetail
            )
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("pokemonItemTest")

        composeTestRule
            .onNodeWithContentDescription("Type DRAGON")
            .assertExists()
    }

    @Test
    fun pokemonItemTest_secondPokemonImageType() {
        composeTestRule.setContent {
            PokemonItem(
                pokemonAndDetail = pokemonAndDetail
            )
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("pokemonItemTest")

        composeTestRule
            .onNodeWithContentDescription("Type FIGHTING")
            .assertExists()
    }

    @Test
    fun pokemonItemTest_pokemonTypeName() {
        composeTestRule.setContent {
            PokemonItem(
                pokemonAndDetail = pokemonAndDetail
            )
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("pokemonItemTest")

        composeTestRule
            .onNodeWithText("Dragon")
            .assertExists()
    }

    @Test
    fun pokemonItemTest_secondPokemonTypeName() {
        composeTestRule.setContent {
            PokemonItem(
                pokemonAndDetail = pokemonAndDetail
            )
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("pokemonItemTest")

        composeTestRule
            .onNodeWithText("Fighting")
            .assertExists()
    }

    @Test
    fun pokemonItemTest_imageWeightInKilogram() {
        composeTestRule.setContent {
            PokemonItem(
                pokemonAndDetail = pokemonAndDetail
            )
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("pokemonItemTest")

        composeTestRule
            .onNodeWithContentDescription("weight in kilogram")
            .assertExists()
    }

    @Test
    fun pokemonItemTest_valueTextKilogram() {
        composeTestRule.setContent {
            PokemonItem(
                pokemonAndDetail = pokemonAndDetail
            )
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("pokemonItemTest")

        composeTestRule
            .onNodeWithText("23.8 kg")
            .assertExists()
    }

    @Test
    fun pokemonItemTest_textWeight() {
        composeTestRule.setContent {
            PokemonItem(
                pokemonAndDetail = pokemonAndDetail
            )
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("pokemonItemTest")

        composeTestRule
            .onNodeWithText("Weight")
            .assertExists()
    }

    @Test
    fun pokemonItemTest_imageHeightInMeters() {
        composeTestRule.setContent {
            PokemonItem(
                pokemonAndDetail = pokemonAndDetail
            )
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("pokemonItemTest")

        composeTestRule
            .onNodeWithContentDescription("height in meters")
            .assertExists()
    }

    @Test
    fun pokemonItemTest_valueTextHeight() {
        composeTestRule.setContent {
            PokemonItem(
                pokemonAndDetail = pokemonAndDetail
            )
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("pokemonItemTest")

        composeTestRule
            .onNodeWithText("1.3 m")
            .assertExists()
    }

    @Test
    fun pokemonItemTest_textHeight() {
        composeTestRule.setContent {
            PokemonItem(
                pokemonAndDetail = pokemonAndDetail
            )
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("pokemonItemTest")

        composeTestRule
            .onNodeWithText("Height")
            .assertExists()
    }
}
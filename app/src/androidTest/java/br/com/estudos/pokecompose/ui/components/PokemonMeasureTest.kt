package br.com.estudos.pokecompose.ui.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import br.com.estudos.pokecompose.R
import br.com.estudos.pokecompose.extensions.toDoubleFormat
import org.junit.Rule
import org.junit.Test

class PokemonMeasureTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun pokemonMeasureTest() {
        composeTestRule.setContent {
            PokemonMeasure(
                formattedMeasure = 253.toDoubleFormat(2),
                iconId = R.drawable.ruler_square,
                iconDescription = R.string.height,
                iconContentDescription = R.string.pokemon_height_image_description
            )
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("measureTest")

        composeTestRule
            .onNodeWithText("Height")
            .assertExists()
    }

    @Test
    fun pokemonMeasureTest_contentDescriptionExist() {
        composeTestRule.setContent {
            PokemonMeasure(
                formattedMeasure = 253.toDoubleFormat(2),
                iconId = R.drawable.ruler_square,
                iconDescription = R.string.height,
                iconContentDescription = R.string.pokemon_height_image_description
            )
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("measureTest")

        composeTestRule
            .onNodeWithContentDescription("height in meters")
            .assertExists()
    }
}
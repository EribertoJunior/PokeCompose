package br.com.estudos.pokecompose.ui.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import br.com.estudos.pokecompose.data.model.local.enums.TypeColoursEnum
import br.com.estudos.pokecompose.ui.theme.PokeComposeTheme
import org.junit.Rule
import org.junit.Test
import java.util.*

class PokemonTypeTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun pokemonTypeTest_anItemToShow() {
        val typeColoursEnum = TypeColoursEnum.FIRE
        composeTestRule.setContent {
            PokeComposeTheme {
                PokemonType(typeColoursEnum = typeColoursEnum)
            }
        }

        composeTestRule.onRoot().printToLog("currentLabelExists")

        composeTestRule
            .onNodeWithText(typeColoursEnum.name.lowercase().replaceFirstChar {
                it.titlecase(Locale.getDefault())
            }
            )
            .assertExists()
    }

    @Test
    fun pokemonTypeTest_contentDescriptionExist() {
        val typeColoursEnum = TypeColoursEnum.FIRE
        composeTestRule.setContent {
            PokeComposeTheme {
                PokemonType(typeColoursEnum = typeColoursEnum)
            }
        }

        composeTestRule.onRoot().printToLog("currentLabelExists")

        composeTestRule
            .onNodeWithContentDescription("Type FIRE")
            .assertExists()
    }

}
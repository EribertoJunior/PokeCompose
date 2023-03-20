package br.com.estudos.pokecompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.compose.ui.unit.dp
import br.com.estudos.pokecompose.R
import br.com.estudos.pokecompose.data.dataBase.local.entities.PokemonDetailStats
import br.com.estudos.pokecompose.data.dataBase.local.entities.Stat
import br.com.estudos.pokecompose.data.model.local.enums.TypeColoursEnum
import br.com.estudos.pokecompose.extensions.color
import br.com.estudos.pokecompose.ui.theme.PokeComposeTheme
import org.junit.Rule
import org.junit.Test

class ProgressBarStatTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun progressBarStatTest() {
        composeTestRule.setContent {
            PokeComposeTheme {

                val progressBarStatContentDescription = stringResource(
                    R.string.content_description_progress_bar_stat,
                    "hp"
                )
                ProgressBarStat(
                    modifier = Modifier
                        .clearAndSetSemantics {
                            contentDescription = progressBarStatContentDescription
                        }
                        .clip(RoundedCornerShape(15.dp))
                        .height(20.dp)
                        .width(200.dp)
                        .background(Color.Gray),
                    colors = listOf(
                        TypeColoursEnum.FIRE.codColor.color,
                        TypeColoursEnum.DRAGON.codColor.color
                    ),
                    pokemonDetailStats = PokemonDetailStats(
                        baseStat = 90,
                        effort = 2,
                        stat = Stat(
                            name = "HP",
                            url = ""
                        )
                    ),
                    widthOfInnerBar = 200.dp
                )
            }
        }

        composeTestRule.onRoot().printToLog("progressBarStatTest")

        composeTestRule
            .onNodeWithContentDescription("hp progress bar")
            .assertExists()
    }
}
package br.com.estudos.pokecompose.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.estudos.pokecompose.data.dataBase.local.entities.PokemonDetailStats
import br.com.estudos.pokecompose.data.dataBase.local.entities.Stat
import br.com.estudos.pokecompose.data.model.local.enums.TypeColoursEnum
import br.com.estudos.pokecompose.extensions.color
import br.com.estudos.pokecompose.ui.theme.PokeComposeTheme

@Composable
fun ProgressBarStat(
    modifier: Modifier = Modifier,
    widthOfInnerBar: Int,
    colorTypeList: List<TypeColoursEnum>,
    pokemonDetailStats: PokemonDetailStats
) {

    ProgressBarStat(
        modifier = modifier,
        widthOfInnerBar = widthOfInnerBar.dp,
        pokemonDetailStats = pokemonDetailStats,
        colors = if (colorTypeList.size == 1) {
            listOf(
                colorTypeList[0].codColor.color,
                colorTypeList[0].codColor.color
            )
        } else {
            colorTypeList.map { it.codColor.color }
        }
    )
}

@Composable
fun ProgressBarStat(
    modifier: Modifier = Modifier,
    widthOfInnerBar: Dp,
    colors: List<Color>,
    pokemonDetailStats: PokemonDetailStats
) {
    Box(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .background(Brush.horizontalGradient(
                    colors = colors.map {
                        it
                    }
                ))
                .width(widthOfInnerBar * pokemonDetailStats.baseStat / 100)
        ) {
            Text(
                text = "${pokemonDetailStats.baseStat}",
                modifier = Modifier.align(Alignment.Center),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun ProgressBarStatPreview() {
    PokeComposeTheme {
        Surface {
            ProgressBarStat(
                modifier = Modifier
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
}
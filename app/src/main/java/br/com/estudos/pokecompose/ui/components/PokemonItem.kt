package br.com.estudos.pokecompose.ui.components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.estudos.pokecompose.R
import br.com.estudos.pokecompose.extensions.color
import br.com.estudos.pokecompose.model.local.Pokemon
import br.com.estudos.pokecompose.model.local.enums.TypeColoursEnum
import coil.compose.rememberAsyncImagePainter

@Composable
fun PokemonItem(pokemon: Pokemon) {
    Card(shape = RoundedCornerShape(8.dp), elevation = 8.dp) {
        Row(
            Modifier
                .fillMaxWidth()
                .heightIn(min = 150.dp, max = 180.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(180.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            pokemon.colorTypeList
                                .map { it.codColor.color }
                                .plus(Color.Transparent)
                        )
                    )
            ) {
                Column(Modifier.align(Center)) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = pokemon.imageUrl,
                            error = painterResource(
                                id = R.drawable.ic_launcher_background
                            ),
                            placeholder = painterResource(id = R.drawable.pokebola)
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .size(130.dp)
                            .align(CenterHorizontally),
                        /*.clip(CircleShape)*/
                        contentScale = ContentScale.Crop,
                    )
                    Text(
                        text = "N°${pokemon.id}",
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .align(CenterHorizontally)
                    )

                }

            }

            Column(modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = pokemon.name,
                    fontSize = 18.sp,
                    modifier = Modifier.align(CenterHorizontally).padding(top = 8.dp)
                )

                Row(
                    modifier = Modifier.padding(top = 8.dp).padding(end = 4.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp, alignment = CenterHorizontally),

                ) {
                    pokemon.colorTypeList.forEach {
                        Image(
                            painter = painterResource(
                                id = getDrawableId(
                                    typeName = it.name,
                                    LocalContext.current
                                )
                            ), contentDescription = "Tipo ${it.name}",
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp),
                            colorFilter = ColorFilter.tint(it.codColor.color)

                        )

                    }
                }
            }
        }
    }
}

private fun getDrawableId(typeName: String, context: Context) =
    context.resources.getIdentifier("ic_${typeName.lowercase()}", "drawable", context.packageName)

@Preview(showBackground = true)
@Composable
fun PokemonItemPreview() {
    PokemonItem(
        Pokemon(
            id = 0,
            name = "Teste",
            listOf(
                TypeColoursEnum.DRAGON,
                TypeColoursEnum.FIRE
            ),
            imageUrl = ""
        )
    )
}

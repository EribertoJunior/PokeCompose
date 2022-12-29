package br.com.estudos.pokecompose.ui.components

import android.content.Context
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.estudos.pokecompose.R
import br.com.estudos.pokecompose.extensions.color
import br.com.estudos.pokecompose.extensions.toDoubleFormat
import br.com.estudos.pokecompose.model.local.Home
import br.com.estudos.pokecompose.model.local.OfficialArtwork
import br.com.estudos.pokecompose.model.local.Other
import br.com.estudos.pokecompose.model.local.Pokemon
import br.com.estudos.pokecompose.model.local.PokemonDetail
import br.com.estudos.pokecompose.model.local.Sprites
import br.com.estudos.pokecompose.model.local.enums.TypeColoursEnum
import br.com.estudos.pokecompose.ui.theme.PokeComposeTheme
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import java.util.Locale

@Composable
fun PokemonItem(pokemon: Pokemon) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp) {
        Row(
            Modifier
                .fillMaxWidth()
                .heightIn(min = 150.dp, max = 180.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.5f)
                    .background(
                        brush = Brush.horizontalGradient(
                            pokemon.pokemonDetail.colorTypeList
                                .map { it.codColor.color }
                                .plus(Color.Transparent)
                        )
                    )
            ) {
                Column(Modifier.align(Center)) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            //model = pokemon.imageUrl,
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(pokemon.imageUrl)
                                .crossfade(true)
                                .build(),
                            error = painterResource(
                                id = R.drawable.pokebola
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
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .align(CenterHorizontally)
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = pokemon.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .padding(top = 8.dp)
                )

                Row(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .padding(end = 4.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        space = 4.dp,
                        alignment = CenterHorizontally
                    )
                ) {
                    pokemon.pokemonDetail.colorTypeList.forEach {
                        PokemonType(it)
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .align(CenterHorizontally)
                ) {
                    PokemonMeasure(
                        formattedMeasure = "${pokemon.pokemonDetail.weight.toDoubleFormat(2)} kg",
                        iconId = R.drawable.weight_kilogram,
                        iconDescription = R.string.weight,
                        iconContentDescription = R.string.pokemon_weight_image_description
                    )
                    PokemonMeasure(
                        formattedMeasure = "${pokemon.pokemonDetail.height.toDoubleFormat(2)} m",
                        iconId = R.drawable.ruler_square,
                        iconDescription = R.string.height,
                        iconContentDescription = R.string.pokemon_height_image_description
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PokemonItemPreview() {
    PokeComposeTheme {
        Surface {
            PokemonItem(
                Pokemon(
                    id = 10,
                    name = "Teste",
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
                        height = 13
                    ),
                    imageUrl = ""
                )
            )
        }
    }
}

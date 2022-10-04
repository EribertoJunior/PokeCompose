package br.com.estudos.pokecompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.estudos.pokecompose.R
import br.com.estudos.pokecompose.model.api.PokemonApi
import br.com.estudos.pokecompose.model.local.Pokemon
import br.com.estudos.pokecompose.model.local.enums.TypeColoursEnum
import coil.compose.rememberAsyncImagePainter

@Composable
fun PokemonItem(pokemon: Pokemon) {
    Card(shape = RoundedCornerShape(8.dp), elevation = 8.dp) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(150.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(180.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            pokemon.colorTypeList
                        )
                    )
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = pokemon.imageUrl,
                        error = painterResource(
                            id = R.drawable.ic_launcher_background
                        )
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(130.dp)
                        .align(Alignment.Center)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                )
            }
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = pokemon.name,
                        fontSize = 18.sp
                    )
                    Text(
                        text = "N°",
                        // text = "N°${pokemon.id}",
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .align(alignment = Alignment.Bottom)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonItemPreview() {
    PokemonItem(
        Pokemon(
            id = 0,
            name = "Teste",
            listOf(
                TypeColoursEnum.FIRE.color,
                Color.Transparent
            ),
            imageUrl = ""
        )
    )
}

package br.com.estudos.pokecompose.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
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
import br.com.estudos.pokecompose.extensions.titlecase
import br.com.estudos.pokecompose.model.local.Pokemon
import br.com.estudos.pokecompose.samples.listPokemonSample
import br.com.estudos.pokecompose.ui.theme.PokeComposeTheme
import br.com.estudos.pokecompose.viewmodels.DetailsViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun DetailsScreen(namePokemon: String, viewModel: DetailsViewModel) {
    val pokemonName = rememberSaveable { namePokemon }
    val pokemonState by viewModel.uiState.collectAsState()

    viewModel.searchEvolutionChain(pokemonName = pokemonName)
    DetailsScreen(pokemonState)
}

@Composable
fun DetailsScreen(pokemon: Pokemon) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        pokemon.pokemonDetail.colorTypeList
                            .map { it.codColor.color }
                            .plus(Color.Transparent)
                    )
                )
        ) {

            Column(
                Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = pokemon.name.titlecase,
                        fontWeight = FontWeight.Bold,
                        fontSize = 23.sp,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            //.align(Alignment.End)
                    )

                    Text(
                        text = pokemon.idFormatted,
                        fontWeight = FontWeight.Bold,
                        fontSize = 23.sp,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            //.align(Alignment.End)
                    )
                }

                Image(
                    painter = rememberAsyncImagePainter(
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
                        .size(250.dp)
                        .align(Alignment.CenterHorizontally),
                    /*.clip(CircleShape)*/
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetailsScreenPreview() {
    PokeComposeTheme {
        Surface {
            DetailsScreen(pokemon = listPokemonSample[2])
        }
    }
}

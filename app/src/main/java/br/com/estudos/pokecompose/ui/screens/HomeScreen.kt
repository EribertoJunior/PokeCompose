package br.com.estudos.pokecompose.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import br.com.estudos.pokecompose.data.model.local.PokemonAndDetail
import br.com.estudos.pokecompose.ui.components.LoadingAnimation
import br.com.estudos.pokecompose.ui.components.PokemonItem
import br.com.estudos.pokecompose.ui.theme.PokeComposeTheme
import br.com.estudos.pokecompose.ui.viewmodels.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel, onClickPokemon: (PokemonAndDetail) -> Unit = {}) {
    HomeScreen(
        pokemonAndDetailLazyPagingItems = viewModel.uiState.collectAsLazyPagingItems(),
        onClickPokemon
    )
}

@Composable
fun HomeScreen(
    pokemonAndDetailLazyPagingItems: LazyPagingItems<PokemonAndDetail>,
    onClickPokemon: (PokemonAndDetail) -> Unit = {}
) {

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            state = rememberLazyListState(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            items(
                items = pokemonAndDetailLazyPagingItems,
                key = { it.pokemon.pokemonId }) { pokemon ->
                pokemon?.let {
                    PokemonItem(pokemonAndDetail = it, onClickPokemon = onClickPokemon)
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 8.dp, end = 8.dp)
        ) {

            when (pokemonAndDetailLazyPagingItems.loadState.refresh) {
                is LoadState.Error -> Unit
                LoadState.Loading -> {
                    LoadingAnimation()
                }
                is LoadState.NotLoading -> Unit
            }

            when (pokemonAndDetailLazyPagingItems.loadState.append) {
                is LoadState.Error -> Unit
                LoadState.Loading -> {
                    LoadingAnimation()
                }
                is LoadState.NotLoading -> Unit
            }
        }
    }
}

@Preview(showBackground = true)
@Preview("Pokemon List Content - Pixel 2", device = Devices.PIXEL_2)
@Preview("Pokemon List Content - Pixel 4", device = Devices.PIXEL_4)
@Preview("Pokemon List Content - Nexus 5", device = Devices.NEXUS_5)
@Preview("Pokemon List Content - Nexus 6", device = Devices.NEXUS_6)
@Preview("Pokemon List (big font)", fontScale = 1.5f)
@Preview("Pokemon List (small screen)", widthDp = 320, heightDp = 480)
@Composable
fun PokemonListPreview() {
    PokeComposeTheme {
        Surface {
            //HomeScreen(pokemonList = flowOf(PagingData.from(listPokemonSample)).collectAsLazyPagingItems())
        }
    }
}
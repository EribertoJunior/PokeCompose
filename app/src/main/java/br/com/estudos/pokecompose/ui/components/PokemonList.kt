package br.com.estudos.pokecompose.ui.components

import android.view.Surface
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import br.com.estudos.pokecompose.model.local.Pokemon
import br.com.estudos.pokecompose.samples.listPokemonSample
import br.com.estudos.pokecompose.ui.theme.PokeComposeTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun PokemonList(pokemonList: LazyPagingItems<Pokemon>) {

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            state = rememberLazyListState(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            items(items = pokemonList, key = { it.id }) { pokemon ->
                pokemon?.let {
                    PokemonItem(pokemon = it)
                }
            }
        }

        Box(modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(bottom = 8.dp, end = 8.dp)) {

            when (pokemonList.loadState.refresh) {
                is LoadState.Error -> Unit
                LoadState.Loading -> {
                    LoadingAnimation()
                }
                is LoadState.NotLoading -> Unit
            }

            when (pokemonList.loadState.append) {
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
@Composable
fun PokemonListPreview() {
    PokeComposeTheme {
        Surface {
            PokemonList(pokemonList = flowOf(PagingData.from(listPokemonSample)).collectAsLazyPagingItems())
        }
    }
}
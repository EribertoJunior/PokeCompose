package br.com.estudos.pokecompose.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import br.com.estudos.pokecompose.model.local.Pokemon

@Composable
fun PokemonList(pokemonList: LazyPagingItems<Pokemon>) {

    LazyColumn(
        Modifier
            //.verticalScroll(rememberScrollState())
            //.fillMaxHeight()
            .padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(pokemonList) { pokemon ->
            pokemon?.let {
                PokemonItem(pokemon = it)
            }
        }

        when (pokemonList.loadState.append) {
            is LoadState.Error -> {
                item {

                }
            }
            LoadState.Loading -> {
                item {
                    LoadingItem()
                }
            }
            is LoadState.NotLoading -> Unit
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonListPreview() {
    // PokemonList(
    //     listPokemonSample.asFlow()
    // )
}
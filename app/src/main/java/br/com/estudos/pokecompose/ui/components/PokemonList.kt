package br.com.estudos.pokecompose.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ListItem
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import br.com.estudos.pokecompose.model.api.PokemonApi
import br.com.estudos.pokecompose.model.local.Pokemon
import br.com.estudos.pokecompose.repository.RepositoryImpl.Companion.MAX_SIZE
import br.com.estudos.pokecompose.repository.RepositoryImpl.Companion.PREFETCH_SIZE
import br.com.estudos.pokecompose.samples.listPokemonSample
import br.com.estudos.pokecompose.ui.theme.PokeComposeTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun PokemonList(pokemonList: LazyPagingItems<Pokemon>) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        state = rememberLazyListState(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        when(pokemonList.loadState.refresh){
            is LoadState.Error -> Unit
            LoadState.Loading -> {
                item {
                    LoadingItem()
                }
            }
            is LoadState.NotLoading -> Unit
        }

        items(items = pokemonList, key = {it.id}) { pokemon ->
            pokemon?.let {
                PokemonItem(pokemon = it)
            }
        }

        when(pokemonList.loadState.append){
            is LoadState.Error -> Unit
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
    PokeComposeTheme {
        Surface {
            PokemonList(pokemonList = flowOf(PagingData.from(listPokemonSample)).collectAsLazyPagingItems())
        }
    }
}
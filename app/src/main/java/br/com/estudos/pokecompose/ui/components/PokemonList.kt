package br.com.estudos.pokecompose.ui.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.estudos.pokecompose.model.local.Pokemon
import br.com.estudos.pokecompose.samples.listPokemonSample

@Composable
fun PokemonList(pokemonList: List<Pokemon>) {

    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Spacer(modifier = Modifier)
        pokemonList.forEach { pokemon: Pokemon ->
            PokemonItem(pokemon = pokemon)
        }
        Spacer(modifier = Modifier)
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonListPreview() {
    PokemonList(
        listPokemonSample
    )
}
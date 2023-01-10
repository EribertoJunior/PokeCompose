package br.com.estudos.pokecompose.ui.uistates

import androidx.paging.PagingData
import br.com.estudos.pokecompose.repository.local.entities.Pokemon

data class HomeScreenUiState(
    var pokemonList: PagingData<Pokemon> = PagingData.from(emptyList())
)
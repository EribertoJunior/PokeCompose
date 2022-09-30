package br.com.estudos.pokecompose.viewmodel

import br.com.estudos.pokecompose.model.local.Pokemon

sealed class HomeUiState {
    object Empy : HomeUiState()
    object Loading : HomeUiState()
    class Loaded(val data: List<Pokemon>) : HomeUiState()
    class Error(val message: String) : HomeUiState()
}

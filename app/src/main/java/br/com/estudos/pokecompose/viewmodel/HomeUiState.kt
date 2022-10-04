package br.com.estudos.pokecompose.viewmodel

sealed class HomeUiState<out H> {
    object Empy : HomeUiState<Unit>()
    object Loading : HomeUiState<Unit>()
    class Loaded<H>(val data: H) : HomeUiState<Any>()
    class Error(val message: String) : HomeUiState<Unit>()
}

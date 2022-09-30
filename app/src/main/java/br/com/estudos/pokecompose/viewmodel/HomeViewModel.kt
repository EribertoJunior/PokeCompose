package br.com.estudos.pokecompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.estudos.pokecompose.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) : ViewModel() {

    private val _homeState = MutableStateFlow<HomeUiState>(HomeUiState.Empy)
    val homeState: StateFlow<HomeUiState> = _homeState

    init {
        fetchPokemons()
    }

    private fun fetchPokemons() {
        _homeState.value = HomeUiState.Loading
        viewModelScope.launch (Dispatchers.IO) {
            val pokemonList = repository.getPokemonList()
            _homeState.value = HomeUiState.Loaded(pokemonList)
        }
    }
}
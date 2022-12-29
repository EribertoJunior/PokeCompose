package br.com.estudos.pokecompose.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import br.com.estudos.pokecompose.repository.Repository
import br.com.estudos.pokecompose.ui.uistates.HomeScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) : ViewModel() {

    private var _uiState: MutableStateFlow<HomeScreenUiState> = MutableStateFlow(HomeScreenUiState())
    val uiState get() = _uiState.asStateFlow()

    init {
        fetchPokemons()
    }

    private fun fetchPokemons() {
        viewModelScope.launch {
            repository.getPokemonList().cachedIn(viewModelScope).collect {
                //_stateFlow.value = it
                _uiState.value = _uiState.value.copy(
                    pokemonList = it
                )
            }
        }
    }
}
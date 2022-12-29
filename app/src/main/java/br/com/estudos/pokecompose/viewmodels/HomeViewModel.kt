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

    private var _stateFlow: MutableStateFlow<HomeScreenUiState> = MutableStateFlow(HomeScreenUiState())
    val stateFlow get() = _stateFlow.asStateFlow()

    init {
        fetchPokemons()
    }

    private fun fetchPokemons() {
        viewModelScope.launch {
            repository.getPokemonList().cachedIn(viewModelScope).collect {
                //_stateFlow.value = it
                _stateFlow.value = _stateFlow.value.copy(
                    pokemonList = it
                )
            }
        }
    }
}
package br.com.estudos.pokecompose.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import br.com.estudos.pokecompose.model.local.Pokemon
import br.com.estudos.pokecompose.repository.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {

    private var _uiState: MutableStateFlow<PagingData<Pokemon>> = MutableStateFlow(PagingData.from(emptyList()))
    val uiState get() = _uiState.asStateFlow()

    init {
        fetchPokemons()
    }

    private fun fetchPokemons() {
        viewModelScope.launch {
            repository.getPokemonList().cachedIn(viewModelScope).collect {
                _uiState.value = it
            }
        }
    }
}
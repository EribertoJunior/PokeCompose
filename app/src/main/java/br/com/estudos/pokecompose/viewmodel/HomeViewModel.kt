package br.com.estudos.pokecompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import br.com.estudos.pokecompose.model.local.Pokemon
import br.com.estudos.pokecompose.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) : ViewModel() {

    private var _stateFlow = MutableStateFlow<PagingData<Pokemon>>(PagingData.from(listOf()))
    val stateFlow = _stateFlow.asStateFlow()

    init {
        fetchPokemons()
    }

    private fun fetchPokemons() {
        viewModelScope.launch {
            repository.getPokemonList().cachedIn(viewModelScope).collect{
                _stateFlow.value = it
            }
        }
    }
}
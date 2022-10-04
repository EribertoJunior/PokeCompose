package br.com.estudos.pokecompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import br.com.estudos.pokecompose.repository.Repository

class HomeViewModel(private val repository: Repository) : ViewModel() {

    fun fetchPokemons() = repository.getPokemonList().cachedIn(viewModelScope)
}
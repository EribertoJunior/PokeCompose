package br.com.estudos.pokecompose.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.estudos.pokecompose.data.dataBase.local.entities.Pokemon
import br.com.estudos.pokecompose.data.dataBase.local.entities.PokemonDetail
import br.com.estudos.pokecompose.data.model.local.PokemonAndDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailsViewModel(private val detailRepository: br.com.estudos.pokecompose.data.repository.DetailRepository) : ViewModel() {

    private var _uiState: MutableStateFlow<PokemonAndDetail> =
        MutableStateFlow(
            PokemonAndDetail(
                pokemon = Pokemon(),
                pokemonDetail = PokemonDetail(),
                specieAndEvolutionChain = null
            )
        )
    val uiState get() = _uiState.asStateFlow()

    fun searchEvolutionChain(pokemonName: String) {
        viewModelScope.launch {
            detailRepository.searchPokemonByName(pokemonName).collectLatest {
                _uiState.value = it
            }
        }
    }
}
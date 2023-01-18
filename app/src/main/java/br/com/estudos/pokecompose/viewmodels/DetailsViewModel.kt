package br.com.estudos.pokecompose.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.estudos.pokecompose.model.local.PokemonAndDetail
import br.com.estudos.pokecompose.repository.local.entities.Pokemon
import br.com.estudos.pokecompose.repository.local.entities.PokemonDetail
import br.com.estudos.pokecompose.repository.DetailRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailsViewModel(private val detailRepository: DetailRepository) : ViewModel() {

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
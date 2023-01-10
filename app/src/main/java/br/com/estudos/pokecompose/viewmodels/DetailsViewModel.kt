package br.com.estudos.pokecompose.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.estudos.pokecompose.extensions.getPokeId
import br.com.estudos.pokecompose.model.local.Pokemon
import br.com.estudos.pokecompose.model.local.PokemonDetail
import br.com.estudos.pokecompose.model.local.Species
import br.com.estudos.pokecompose.model.remote.ChainRemote
import br.com.estudos.pokecompose.repository.DetailRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailsViewModel(private val detailRepository: DetailRepository) : ViewModel() {

    private var _uiState: MutableStateFlow<Pokemon> =
        MutableStateFlow(Pokemon(pokemonDetail = PokemonDetail()))
    val uiState get() = _uiState.asStateFlow()

    fun searchEvolutionChain(pokemonName: String) {
        //val evolutionsListIterator = ArrayList<Species>()

        viewModelScope.launch {
            // val pokemonResponse = async { detailRepository.searchPokemonByName(pokemonName) }
            // pokemonResponse.await().let {
            //     it.collectLatest {
            //
            //     }
            //     val pokemonState: Pokemon = it.first()
            //     pokemonState.pokemonDetail
            //
            //     _uiState.value = it.first()
            // }

            detailRepository.searchPokemonByName(pokemonName).collectLatest {
                _uiState.value = it
            }

            // val specieResponse =
            //     async { detailRepository.searchPokemonSpecieByName(pokemonName = pokemonName) }
            // val evolutionChanResponse =
            //     async { detailRepository.searchEvolutionChan(specieResponse.await().evolutionChainAddress.url) }
            //
            // val evolutionChain = evolutionChanResponse.await()
            //
            // evolutionChain.chain.species.run {
            //     evolutionsListIterator.add(
            //         Species(
            //             name = name,
            //             imageUrl = buildUrlImagem(url.getPokeId)
            //         )
            //     )
            // }
            // evolutionsListIterator.addAll(buildListOfEvolutions(evolutionChain.chain.evolvesTo))

        }
    }

    private fun buildListOfEvolutions(chainRemote: List<ChainRemote>): List<Species> {
        val mutableList = arrayListOf<Species>()
        if (chainRemote.isNotEmpty()) {
            chainRemote.forEach {
                mutableList.add(
                    Species(
                        name = it.species.name,
                        imageUrl = buildUrlImagem(it.species.url.getPokeId)
                    ),
                )
                mutableList.addAll(buildListOfEvolutions(it.evolvesTo))
            }
        } else {
            mutableList.addAll(emptyList())
        }
        return mutableList.toList()
    }

    private fun buildUrlImagem(idPokemon: Int) =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$idPokemon.png"
}
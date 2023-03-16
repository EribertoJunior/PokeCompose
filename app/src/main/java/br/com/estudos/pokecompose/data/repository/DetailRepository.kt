package br.com.estudos.pokecompose.data.repository

import br.com.estudos.pokecompose.data.model.local.PokemonAndDetail
import br.com.estudos.pokecompose.data.model.remote.EvolutionChainRemote
import br.com.estudos.pokecompose.data.model.remote.SpeciesRemote
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    suspend fun searchPokemonByName(pokemonName: String): Flow<PokemonAndDetail>
}
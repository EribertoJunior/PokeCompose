package br.com.estudos.pokecompose.repository

import br.com.estudos.pokecompose.model.local.Pokemon
import br.com.estudos.pokecompose.model.remote.EvolutionChainRemote
import br.com.estudos.pokecompose.model.remote.SpeciesRemote
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    suspend fun searchPokemonByName(pokemonName: String): Flow<Pokemon>
    suspend fun searchPokemonSpecieByName(pokemonName: String): SpeciesRemote
    suspend fun searchEvolutionChan(url: String): EvolutionChainRemote
}
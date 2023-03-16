package br.com.estudos.pokecompose.data.dataSource.remote

import br.com.estudos.pokecompose.data.model.remote.EvolutionChainRemote
import br.com.estudos.pokecompose.data.model.remote.ListPokemonRemote
import br.com.estudos.pokecompose.data.model.remote.PokemonDetailRemote
import br.com.estudos.pokecompose.data.model.remote.SpeciesRemote

interface RemoteDataSource {
    suspend fun getListPokemon(
        limit: Int = 10,
        offset: Int
    ): ListPokemonRemote

    suspend fun getPokemonDetails(pokemonName: String) : PokemonDetailRemote

    suspend fun searchPokemonSpecie(pokemonSpecieUrl: String): SpeciesRemote?

    suspend fun searchEvolutionChan(evolutionChanUrl: String): EvolutionChainRemote
}
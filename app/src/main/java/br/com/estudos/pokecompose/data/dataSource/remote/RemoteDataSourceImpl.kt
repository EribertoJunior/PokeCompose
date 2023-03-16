package br.com.estudos.pokecompose.data.dataSource.remote

import br.com.estudos.pokecompose.data.dataBase.remote.PokemonService
import br.com.estudos.pokecompose.data.model.remote.EvolutionChainRemote
import br.com.estudos.pokecompose.data.model.remote.ListPokemonRemote
import br.com.estudos.pokecompose.data.model.remote.PokemonDetailRemote
import br.com.estudos.pokecompose.data.model.remote.SpeciesRemote

class RemoteDataSourceImpl(private val pokemonService: PokemonService) : RemoteDataSource {
    override suspend fun getListPokemon(limit: Int, offset: Int): ListPokemonRemote {
        return pokemonService.getListPokemon(limit = limit, offset = offset)
    }

    override suspend fun getPokemonDetails(pokemonName: String): PokemonDetailRemote {
        return pokemonService.getPokemonDetails(pokemonName)
    }

    override suspend fun searchPokemonSpecie(pokemonSpecieUrl: String): SpeciesRemote? {
        return pokemonService.searchPokemonSpecie(pokemonSpecieUrl)
    }

    override suspend fun searchEvolutionChan(evolutionChanUrl: String): EvolutionChainRemote {
        return pokemonService.searchEvolutionChan(evolutionChanUrl)
    }
}
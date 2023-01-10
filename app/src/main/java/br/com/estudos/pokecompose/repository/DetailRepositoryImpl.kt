package br.com.estudos.pokecompose.repository

import br.com.estudos.pokecompose.model.local.PokemonAndDetail
import br.com.estudos.pokecompose.model.remote.EvolutionChainRemote
import br.com.estudos.pokecompose.model.remote.SpeciesRemote
import br.com.estudos.pokecompose.repository.local.PokemonDao
import br.com.estudos.pokecompose.repository.remote.PokemonService
import kotlinx.coroutines.flow.Flow

class DetailRepositoryImpl(
    private val pokemonDao: PokemonDao,
    private val pokemonService: PokemonService
) : DetailRepository {

    override suspend fun searchPokemonByName(pokemonName: String): Flow<PokemonAndDetail> {

        return pokemonDao.searchPokemonByName(pokemonName)
    }

    override suspend fun searchPokemonSpecieByName(pokemonName: String): SpeciesRemote {
        return pokemonService.searchPokemonSpecieByName(pokemonName = pokemonName)
    }

    override suspend fun searchEvolutionChan(url: String): EvolutionChainRemote {
        return pokemonService.searchEvolutionChan(url)
    }
}
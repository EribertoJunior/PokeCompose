package br.com.estudos.pokecompose.data.repository

import br.com.estudos.pokecompose.data.dataBase.local.PokemonDao
import br.com.estudos.pokecompose.data.dataBase.remote.PokemonService
import br.com.estudos.pokecompose.data.model.local.PokemonAndDetail
import kotlinx.coroutines.flow.Flow

class DetailRepositoryImpl(
    private val pokemonDao: PokemonDao,
    private val pokemonService: PokemonService
) : DetailRepository {

    override suspend fun searchPokemonByName(pokemonName: String): Flow<PokemonAndDetail> {
        return pokemonDao.searchPokemonByName(pokemonName)
    }
}
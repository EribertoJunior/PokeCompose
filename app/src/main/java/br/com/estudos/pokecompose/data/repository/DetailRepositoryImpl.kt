package br.com.estudos.pokecompose.data.repository

import br.com.estudos.pokecompose.data.dataSource.local.LocalDataSource
import br.com.estudos.pokecompose.data.model.local.PokemonAndDetail
import kotlinx.coroutines.flow.Flow

class DetailRepositoryImpl(
    private val localDataSource: LocalDataSource
) : DetailRepository {

    override suspend fun searchPokemonByName(pokemonName: String): Flow<PokemonAndDetail> {
        return localDataSource.searchPokemonByName(pokemonName)
    }
}
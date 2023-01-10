package br.com.estudos.pokecompose.repository

import androidx.paging.PagingData
import br.com.estudos.pokecompose.model.local.PokemonAndDetail
import br.com.estudos.pokecompose.repository.local.entities.Pokemon
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getPokemonList(): Flow<PagingData<PokemonAndDetail>>
}
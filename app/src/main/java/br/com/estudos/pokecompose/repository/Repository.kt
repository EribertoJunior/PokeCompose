package br.com.estudos.pokecompose.repository

import androidx.paging.PagingData
import br.com.estudos.pokecompose.model.api.PokemonApi
import br.com.estudos.pokecompose.model.local.Pokemon
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getPokemonList(): Flow<PagingData<Pokemon>>
}
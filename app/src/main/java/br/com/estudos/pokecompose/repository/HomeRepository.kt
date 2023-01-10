package br.com.estudos.pokecompose.repository

import androidx.paging.PagingData
import br.com.estudos.pokecompose.model.local.Pokemon
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getPokemonList(): Flow<PagingData<Pokemon>>
}
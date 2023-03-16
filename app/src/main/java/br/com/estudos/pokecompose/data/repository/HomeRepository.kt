package br.com.estudos.pokecompose.data.repository

import androidx.paging.PagingData
import br.com.estudos.pokecompose.data.model.local.PokemonAndDetail
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getPokemonList(): Flow<PagingData<PokemonAndDetail>>
}
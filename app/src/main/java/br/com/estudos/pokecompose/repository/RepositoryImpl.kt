package br.com.estudos.pokecompose.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.estudos.pokecompose.model.api.PokemonApi
import br.com.estudos.pokecompose.model.local.Pokemon
import br.com.estudos.pokecompose.repository.remote.PokemonDataSource
import br.com.estudos.pokecompose.samples.listPokemonSample
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

class RepositoryImpl(private val pokemonDataSource: PokemonDataSource) : Repository {
    fun getPokemonListPagingSource(): Flow<PagingData<PokemonApi>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                maxSize = MAX_SIZE,
                prefetchDistance = PREFETCH_SIZE
            ),
            pagingSourceFactory = { pokemonDataSource }
        ).flow
    }


    companion object {
        const val PAGE_SIZE = 10
        const val PREFETCH_SIZE = 50
        const val MAX_SIZE = PAGE_SIZE + PREFETCH_SIZE * 3
    }

    override fun getPokemonList(): Flow<PagingData<Pokemon>> {
        TODO("Not yet implemented")
    }
}
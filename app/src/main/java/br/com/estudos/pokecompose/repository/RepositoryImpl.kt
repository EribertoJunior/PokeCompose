package br.com.estudos.pokecompose.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.estudos.pokecompose.model.local.Pokemon
import br.com.estudos.pokecompose.repository.PokemonRemoteMediator.Companion.PAGE_SIZE
import br.com.estudos.pokecompose.repository.local.PokemonDao
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val pokemonRemoteMediator: PokemonRemoteMediator,
    private val pokemonDao: PokemonDao
) : Repository {
    // fun getPokemonListPagingSource(): Flow<PagingData<PokemonApi>> {
    //     return Pager(
    //         config = PagingConfig(
    //             pageSize = PAGE_SIZE,
    //             maxSize = MAX_SIZE,
    //             prefetchDistance = PREFETCH_SIZE
    //         ),
    //         pagingSourceFactory = { pokemonDataSource }
    //     ).flow
    // }

    @OptIn(ExperimentalPagingApi::class)
    override fun getPokemonList(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                //maxSize = MAX_SIZE,
                prefetchDistance = PREFETCH_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = pokemonRemoteMediator,
            pagingSourceFactory = { pokemonDao.getPokemons() }
        ).flow
    }

    companion object {
        const val PREFETCH_SIZE = 100
        const val MAX_SIZE = (PAGE_SIZE + PREFETCH_SIZE) * 3
    }
}
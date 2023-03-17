package br.com.estudos.pokecompose.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.estudos.pokecompose.data.dataBase.local.PokemonDao
import br.com.estudos.pokecompose.data.model.local.PokemonAndDetail
import br.com.estudos.pokecompose.data.repository.PokemonRemoteMediator.Companion.PAGE_SIZE
import kotlinx.coroutines.flow.Flow

class HomeRepositoryImpl(
    private val pokemonRemoteMediator: PokemonRemoteMediator,
    private val pokemonDao: PokemonDao
) : HomeRepository {

    @ExperimentalPagingApi
    override fun getPokemonList(): Flow<PagingData<PokemonAndDetail>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                maxSize = MAX_SIZE,
                initialLoadSize = PAGE_SIZE + PREFETCH_SIZE,
                prefetchDistance = PREFETCH_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = pokemonRemoteMediator,
            pagingSourceFactory = { pokemonDao.getPokemons() }
        ).flow
    }

    companion object {
        private const val PREFETCH_SIZE = 50
        private const val MAX_SIZE = PAGE_SIZE + (PREFETCH_SIZE * 2)
    }
}
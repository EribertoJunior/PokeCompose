package br.com.estudos.pokecompose.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.estudos.pokecompose.data.dataBase.local.PokemonDao
import br.com.estudos.pokecompose.data.model.local.PokemonAndDetail
import br.com.estudos.pokecompose.data.repository.PokemonRemoteMediator.Companion.PAGE_SIZE

class HomeRepositoryImpl(
    private val pokemonRemoteMediator: br.com.estudos.pokecompose.data.repository.PokemonRemoteMediator,
    private val pokemonDao: PokemonDao
) : br.com.estudos.pokecompose.data.repository.HomeRepository {

    @ExperimentalPagingApi
    override fun getPokemonList(): kotlinx.coroutines.flow.Flow<PagingData<PokemonAndDetail>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                maxSize = br.com.estudos.pokecompose.data.repository.HomeRepositoryImpl.Companion.MAX_SIZE,
                initialLoadSize = PAGE_SIZE + br.com.estudos.pokecompose.data.repository.HomeRepositoryImpl.Companion.PREFETCH_SIZE,
                prefetchDistance = br.com.estudos.pokecompose.data.repository.HomeRepositoryImpl.Companion.PREFETCH_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = pokemonRemoteMediator,
            pagingSourceFactory = { pokemonDao.getPokemons() }
        ).flow
    }

    companion object {
        const val PREFETCH_SIZE = 50
        const val MAX_SIZE = PAGE_SIZE + (br.com.estudos.pokecompose.data.repository.HomeRepositoryImpl.Companion.PREFETCH_SIZE * 2)
    }
}
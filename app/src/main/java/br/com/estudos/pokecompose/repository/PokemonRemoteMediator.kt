package br.com.estudos.pokecompose.repository

import android.net.Uri
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import br.com.estudos.pokecompose.model.local.Pokemon
import br.com.estudos.pokecompose.model.local.PokemonRemoteKey
import br.com.estudos.pokecompose.repository.local.PokemonDao
import br.com.estudos.pokecompose.repository.local.PokemonRemoteKeyDao
import br.com.estudos.pokecompose.repository.remote.PokemonService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    private val pokemonDao: PokemonDao,
    private val pokemonRemoteKeyDao: PokemonRemoteKeyDao,
    private val pokemonService: PokemonService
) : RemoteMediator<Int, Pokemon>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Pokemon>
    ): MediatorResult {
        return try {
            val offSet = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKey = getClosestRemoteKeyToCurrentPosition(state)
                    remoteKey?.nextOffset?.minus(OFFSET) ?: 0
                }
                LoadType.PREPEND -> {
                    val remoteKey = getRemoteKeyForFirstItem(state)
                    val prevKey = remoteKey?.prevOffset ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKey != null
                    )
                    prevKey
                }
                LoadType.APPEND -> {
                    val remoteKey = getRemoteKeyForLastItem(state)
                    val nextKey = remoteKey?.nextOffset ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKey != null
                    )
                    nextKey
                }
            }

            coroutineScope {
                withContext(IO) {
                    val response = pokemonService.getListPokemon(limit = PAGE_SIZE, offset = offSet)
                    val pokemonList = response.results
                    val endOfPaginationReached = pokemonList.isEmpty()
                    val prevKey = getOffsetParameter(response.previous)
                    val nextKey = getOffsetParameter(response.next)

                    // if(loadType == LoadType.REFRESH){
                    //     pokemonDao.deleteAll()
                    //     pokemonRemoteKeyDao.deleteAll()
                    // }

                    val listPokemonRemoteKey: ArrayList<PokemonRemoteKey> = arrayListOf()
                    val listPokemon: ArrayList<Pokemon> = arrayListOf()

                    pokemonList.forEach {
                        val pokemonId = getIdPokemon(it.url)
                        val pokemonDetail = async { pokemonService.getPokemonDetails(pokemonId) }

                        pokemonDetail.await().run {
                            listPokemon.add(
                                Pokemon(
                                    id = pokemonId,
                                    name = it.name,
                                    pokemonDetail = pokeDetailRemoteToPokeDetail(),
                                    imageUrl = sprites.other.officialArtwork.frontDefault
                                )
                            )
                        }

                        listPokemonRemoteKey.add(
                            PokemonRemoteKey(
                                id = pokemonId.toLong(),
                                pokemonName = it.name,
                                prevOffset = prevKey,
                                nextOffset = nextKey
                            )
                        )
                    }

                    pokemonRemoteKeyDao.saveAll(listPokemonRemoteKey)
                    pokemonDao.saveAll(listPokemon)

                    MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
                }
            }
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private fun getIdPokemon(urlPokemon: String): Int {
        val regex = "/\\d+".toRegex()
        return regex.find(urlPokemon)?.value?.replace("/", "")?.toInt() ?: 0
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Pokemon>): PokemonRemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { pokemon ->
                pokemonRemoteKeyDao.getPokemonRemoteKeyFromName(pokemon.name)
            }
    }

    private suspend fun getClosestRemoteKeyToCurrentPosition(state: PagingState<Int, Pokemon>): PokemonRemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.name?.let { pokemonName ->
                pokemonRemoteKeyDao.getPokemonRemoteKeyFromName(pokemonName)
            }
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Pokemon>): PokemonRemoteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { pokemon ->
            pokemonRemoteKeyDao.getPokemonRemoteKeyFromName(pokemon.name)
        }
    }

    fun getOffsetParameter(url: String?): Int? {
        return url?.let {
            Uri.parse(url).getQueryParameter("offset")?.toInt()
        }
    }

    companion object {
        const val PAGE_SIZE = 100
        const val OFFSET = 100
    }
}
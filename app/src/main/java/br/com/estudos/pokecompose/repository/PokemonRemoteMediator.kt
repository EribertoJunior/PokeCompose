package br.com.estudos.pokecompose.repository

import android.net.Uri
import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import br.com.estudos.pokecompose.extensions.getUrlId
import br.com.estudos.pokecompose.model.local.PokemonAndDetail
import br.com.estudos.pokecompose.repository.local.EvolutionChainDao
import br.com.estudos.pokecompose.repository.local.PokemonDao
import br.com.estudos.pokecompose.repository.local.PokemonDetailDao
import br.com.estudos.pokecompose.repository.local.PokemonRemoteKeyDao
import br.com.estudos.pokecompose.repository.local.PokemonSpeciesDao
import br.com.estudos.pokecompose.repository.local.entities.EvolutionChain
import br.com.estudos.pokecompose.repository.local.entities.Pokemon
import br.com.estudos.pokecompose.repository.local.entities.PokemonDetail
import br.com.estudos.pokecompose.repository.local.entities.PokemonRemoteKey
import br.com.estudos.pokecompose.repository.local.entities.PokemonSpecies
import br.com.estudos.pokecompose.repository.remote.PokemonService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    private val pokemonDao: PokemonDao,
    private val pokemonRemoteKeyDao: PokemonRemoteKeyDao,
    private val pokemonDetailDao: PokemonDetailDao,
    private val pokemonSpeciesDao: PokemonSpeciesDao,
    private val evolutionChainDao: EvolutionChainDao,
    private val pokemonService: PokemonService,
) : RemoteMediator<Int, PokemonAndDetail>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonAndDetail>
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
                    val listPokemonDetail: ArrayList<PokemonDetail> = arrayListOf()
                    val listPokemonSpecies: ArrayList<PokemonSpecies> = arrayListOf()
                    val listEvolutionChain: ArrayList<EvolutionChain> = arrayListOf()

                    pokemonList.forEach {
                        val detailRemote = async { pokemonService.getPokemonDetails(it.name) }
                        val speciesRemote =
                            async { pokemonService.searchPokemonSpecieByName(pokemonName = it.name) }

                        detailRemote.await().run {
                            Log.i("Armazenando... >", "id do pokemon: ${it.url.getUrlId}")
                            listPokemon.add(
                                Pokemon(
                                    pokemonId = id,
                                    name = it.name,
                                    imageUrl = sprites.other.officialArtwork.frontDefault
                                )
                            )

                            listPokemonDetail.add(pokeDetailRemoteToPokeDetail())

                            listPokemonRemoteKey.add(
                                PokemonRemoteKey(
                                    id = id.toLong(),
                                    pokemonName = it.name,
                                    prevOffset = prevKey,
                                    nextOffset = nextKey
                                )
                            )
                        }

                        speciesRemote.await().let { species ->
                            listPokemonSpecies.add(species.mapToPokemonSpecie())

                            species.evolutionChainAddressRemote.url.getUrlId.let { idEvolutionChain ->

                                val evolutionChainLocal = withContext(IO) {
                                    evolutionChainDao.searchEvolutionChainById(idEvolutionChain)
                                }

                                if (evolutionChainLocal == null) {
                                    val evolutionChainRemoteDeferred =
                                        async { pokemonService.searchEvolutionChan(species.evolutionChainAddressRemote.url) }

                                    evolutionChainRemoteDeferred.await().run {
                                        listEvolutionChain.add(mapToEvolutionChain())
                                    }
                                }
                            }
                        }
                    }

                    pokemonRemoteKeyDao.saveAll(listPokemonRemoteKey)
                    pokemonDetailDao.saveAll(listPokemonDetail)
                    pokemonDao.saveAll(listPokemon)
                    pokemonSpeciesDao.saveAllSpecie(listPokemonSpecies)
                    evolutionChainDao.saveAll(listEvolutionChain)

                    listEvolutionChain.clear()
                    listPokemon.clear()
                    listPokemonSpecies.clear()
                    listPokemonDetail.clear()
                    listPokemonRemoteKey.clear()

                    MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
                }
            }
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, PokemonAndDetail>): PokemonRemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { pokemonAndDetail ->
                pokemonRemoteKeyDao.getPokemonRemoteKeyFromName(pokemonAndDetail.pokemon.name)
            }
    }

    private suspend fun getClosestRemoteKeyToCurrentPosition(state: PagingState<Int, PokemonAndDetail>): PokemonRemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.pokemon?.name?.let { pokemonName ->
                pokemonRemoteKeyDao.getPokemonRemoteKeyFromName(pokemonName)
            }
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, PokemonAndDetail>): PokemonRemoteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { pokemonAndDetail ->
                pokemonRemoteKeyDao.getPokemonRemoteKeyFromName(pokemonAndDetail.pokemon.name)
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
package br.com.estudos.pokecompose.data.repository

import android.net.Uri
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import br.com.estudos.pokecompose.data.dataBase.local.*
import br.com.estudos.pokecompose.data.dataBase.local.entities.Pokemon
import br.com.estudos.pokecompose.data.dataBase.local.entities.PokemonDetail
import br.com.estudos.pokecompose.data.dataBase.local.entities.PokemonRemoteKey
import br.com.estudos.pokecompose.data.dataBase.local.entities.PokemonSpecies
import br.com.estudos.pokecompose.data.dataSource.local.LocalDataSource
import br.com.estudos.pokecompose.data.dataSource.remote.RemoteDataSource
import br.com.estudos.pokecompose.data.model.local.PokemonAndDetail
import br.com.estudos.pokecompose.data.model.remote.PokemonDetailRemote
import br.com.estudos.pokecompose.data.model.remote.PokemonRemote
import br.com.estudos.pokecompose.extensions.getUrlId
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
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
                    val response = remoteDataSource.getListPokemon(limit = PAGE_SIZE, offset = offSet)
                    val pokemonList = response.results
                    val endOfPaginationReached = pokemonList.isEmpty()
                    val prevKey = getOffsetParameter(response.previous)
                    val nextKey = getOffsetParameter(response.next)

                    // if(loadType == LoadType.REFRESH){
                    //     pokemonDao.deleteAllPokemon()
                    //     pokemonRemoteKeyDao.deleteAllPokemon()
                    // }

                    val listPokemonRemoteKey: ArrayList<PokemonRemoteKey> = arrayListOf()
                    val listPokemon: ArrayList<Pokemon> = arrayListOf()
                    val listPokemonDetail: ArrayList<PokemonDetail> = arrayListOf()
                    val listPokemonSpecies: ArrayList<PokemonSpecies> = arrayListOf()

                    val onPokemonBuilt: (Pokemon) -> Unit = {
                        listPokemon.add(it)
                    }
                    val onPokemonDetailsBuilt: (PokemonDetail) -> Unit = {
                        listPokemonDetail.add(it)
                    }
                    val onPokemonRemoteKeyBuilt: (PokemonRemoteKey) -> Unit = {
                        listPokemonRemoteKey.add(it)
                    }
                    val onSpeciesFound: (PokemonSpecies) -> Unit = { species ->
                        listPokemonSpecies.add(species)
                    }

                    pokemonList.forEach { pokemon ->
                        findDetailsPokemon(
                            pokemon = pokemon,
                            prevKey = prevKey,
                            nextKey = nextKey,
                            onPokemonBuilt = onPokemonBuilt,
                            onPokemonDetailsBuilt = onPokemonDetailsBuilt,
                            onPokemonRemoteKeyBuilt = onPokemonRemoteKeyBuilt,
                            onSpeciesFound = onSpeciesFound
                        )
                    }

                    localDataSource.saveAllRemoteKey(listPokemonRemoteKey)
                    localDataSource.saveAllPokemonDetail(listPokemonDetail)
                    localDataSource.saveAllPokemons(listPokemon)
                    localDataSource.saveAllPokemonSpecies(listPokemonSpecies)

                    MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            MediatorResult.Error(e)
        }
    }

    private suspend fun findDetailsPokemon(
        pokemon: PokemonRemote,
        prevKey: Int?,
        nextKey: Int?,
        onPokemonBuilt: (Pokemon) -> Unit = {},
        onPokemonDetailsBuilt: (PokemonDetail) -> Unit = {},
        onPokemonRemoteKeyBuilt: (PokemonRemoteKey) -> Unit = {},
        onSpeciesFound: (PokemonSpecies) -> Unit = {}
    ) {
        coroutineScope {
            withContext(IO) {
                val detailRemote = async {
                    remoteDataSource.getPokemonDetails(pokemon.name)
                }

                detailRemote.await().run {
                    onPokemonBuilt(
                        Pokemon(
                            pokemonId = id,
                            name = pokemon.name,
                            imageUrl = sprites.other.officialArtwork.frontDefault
                        )
                    )

                    onPokemonDetailsBuilt(mapPokeDetailRemoteToPokeDetail())

                    onPokemonRemoteKeyBuilt(
                        PokemonRemoteKey(
                            id = id.toLong(),
                            pokemonName = pokemon.name,
                            prevOffset = prevKey,
                            nextOffset = nextKey
                        )
                    )

                    findSpeciePokemon(pokemonDetailRemote = this) { species ->
                        onSpeciesFound(species)
                    }
                }
            }

        }
    }

    private suspend fun findSpeciePokemon(
        pokemonDetailRemote: PokemonDetailRemote,
        onSpeciesFound: (PokemonSpecies) -> Unit = {}
    ) {
        withContext(IO) {
            try {
                pokemonDetailRemote.species?.let { detailRemote ->
                    val speciesRemote = async {
                        remoteDataSource.searchPokemonSpecie(pokemonSpecieUrl = detailRemote.url)
                    }
                    speciesRemote.await()?.let { species ->
                        onSpeciesFound(species.mapToPokemonSpecie())

                        species.evolutionChainAddressRemote?.url?.let { evolutionChainUrl ->
                            findEvolutionChainPokemon(evolutionChainUrl)
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun findEvolutionChainPokemon(evolutionChainUrl: String) {
        coroutineScope {
            withContext(IO) {
                if (searchLocalEvolutionChainById(evolutionChainUrl)) {
                    //Dados de ${pokemon.name} não foram encontrados no banco de dados.
                    try {
                        val evolutionChainRemoteDeferred = async {
                            remoteDataSource.searchEvolutionChan(evolutionChainUrl)
                        }

                        evolutionChainRemoteDeferred.await().let { evolutionChainRemote ->
                            localDataSource.saveEvolutionChain(evolutionChainRemote.mapToEvolutionChain())
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    private suspend fun searchLocalEvolutionChainById(evolutionChainUrl: String): Boolean {
        val evolutionChainLocal = withContext(IO) {
            //Buscando dados de evolucao no banco de dados
            localDataSource.searchEvolutionChainById(evolutionChainUrl.getUrlId)
        }
        return evolutionChainLocal == null
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, PokemonAndDetail>): PokemonRemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { pokemonAndDetail ->
                localDataSource.getPokemonRemoteKeyByName(pokemonAndDetail.pokemon.name)
            }
    }

    private suspend fun getClosestRemoteKeyToCurrentPosition(state: PagingState<Int, PokemonAndDetail>): PokemonRemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.pokemon?.name?.let { pokemonName ->
                localDataSource.getPokemonRemoteKeyByName(pokemonName)
            }
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, PokemonAndDetail>): PokemonRemoteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { pokemonAndDetail ->
                localDataSource.getPokemonRemoteKeyByName(pokemonAndDetail.pokemon.name)
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
package br.com.estudos.pokecompose.repository

import android.net.Uri
import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import br.com.estudos.pokecompose.extensions.getUrlId
import br.com.estudos.pokecompose.model.local.PokemonAndDetail
import br.com.estudos.pokecompose.model.remote.PokemonDetailRemote
import br.com.estudos.pokecompose.model.remote.PokemonRemote
import br.com.estudos.pokecompose.repository.local.EvolutionChainDao
import br.com.estudos.pokecompose.repository.local.PokemonDao
import br.com.estudos.pokecompose.repository.local.PokemonDetailDao
import br.com.estudos.pokecompose.repository.local.PokemonRemoteKeyDao
import br.com.estudos.pokecompose.repository.local.PokemonSpeciesDao
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

                    pokemonRemoteKeyDao.saveAll(listPokemonRemoteKey)
                    pokemonDetailDao.saveAll(listPokemonDetail)
                    pokemonDao.saveAll(listPokemon)
                    pokemonSpeciesDao.saveAllSpecie(listPokemonSpecies)

                    MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "load: ${e.message}")
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
                    Log.i(TAG, "Buscando detalhes do pokemon ${pokemon.name} na PokeApi")
                    pokemonService.getPokemonDetails(pokemon.name)
                }

                detailRemote.await().run {
                    Log.i(TAG, "Mapeando Pokemon com id: ${pokemon.url.getUrlId}")
                    onPokemonBuilt(
                        Pokemon(
                            pokemonId = id,
                            name = pokemon.name,
                            imageUrl = sprites.other.officialArtwork.frontDefault
                        )
                    )

                    Log.i(TAG, "Mapeando detalhes do pokemon ${pokemon.name}")
                    onPokemonDetailsBuilt(mapPokeDetailRemoteToPokeDetail())

                    Log.i(TAG, "Mapeando chave remota do pokemon ${pokemon.name}")
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
                        Log.i(
                            TAG,
                            "Buscando dados de especie do pokemon ${detailRemote.name} na PokeApi"
                        )
                        pokemonService.searchPokemonSpecie(pokemonSpecieUrl = detailRemote.url)
                    }
                    speciesRemote.await()?.let { species ->
                        Log.i(TAG, "Mapeando especie do pokemon ${detailRemote.name}")
                        onSpeciesFound(species.mapToPokemonSpecie())

                        species.evolutionChainAddressRemote?.url?.let { evolutionChainUrl ->
                            findEvolutionChainPokemon(evolutionChainUrl)
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "findSpeciePokemon: ${e.message}")
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
                            Log.i(TAG, "Buscando dados de evolucoes na PokeApi")
                            pokemonService.searchEvolutionChan(evolutionChainUrl)
                        }

                        evolutionChainRemoteDeferred.await().let { evolutionChainRemote ->
                            Log.i(
                                TAG, "Armazenando dados de evolucoes de id ${evolutionChainRemote.id} no banco de dados")
                            evolutionChainDao.save(evolutionChainRemote.mapToEvolutionChain())
                        }
                    } catch (e: Exception) {
                        Log.e(TAG, "findEvolutionChainPokemon: ${e.message}")
                    }
                }
            }
        }
    }

    private suspend fun searchLocalEvolutionChainById(evolutionChainUrl: String): Boolean {
        val evolutionChainLocal = withContext(IO) {
            //Buscando dados de evolucao no banco de dados
            evolutionChainDao.searchEvolutionChainById(evolutionChainUrl.getUrlId)
        }
        return evolutionChainLocal == null
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
        private const val TAG = "PokemonRemoteMediator <>"
    }
}
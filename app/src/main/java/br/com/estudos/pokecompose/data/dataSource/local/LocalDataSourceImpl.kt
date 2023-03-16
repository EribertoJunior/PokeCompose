package br.com.estudos.pokecompose.data.dataSource.local

import androidx.paging.PagingSource
import br.com.estudos.pokecompose.data.dataBase.local.*
import br.com.estudos.pokecompose.data.dataBase.local.entities.*
import br.com.estudos.pokecompose.data.model.local.PokemonAndDetail
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl(
    private val pokemonDao: PokemonDao,
    private val pokemonRemoteKeyDao: PokemonRemoteKeyDao,
    private val pokemonDetailDao: PokemonDetailDao,
    private val pokemonSpeciesDao: PokemonSpeciesDao,
    private val evolutionChainDao: EvolutionChainDao,
): LocalDataSource {
    override fun getPokemons(): PagingSource<Int, PokemonAndDetail> {
        return pokemonDao.getPokemons()
    }

    override fun searchPokemonByName(name: String): Flow<PokemonAndDetail> {
        return pokemonDao.searchPokemonByName(name)
    }

    override suspend fun deleteAllPokemon() {
        pokemonDao.deleteAll()
    }

    override suspend fun saveAllPokemons(pokemons: List<Pokemon>) {
        return pokemonDao.saveAll(pokemons)
    }

    override suspend fun savePokemon(pokemon: Pokemon) {
        return pokemonDao.save(pokemon)
    }

    override suspend fun saveAllRemoteKey(pokemonRemoteKeys: List<PokemonRemoteKey>) {
        return pokemonRemoteKeyDao.saveAll(pokemonRemoteKeys)
    }

    override suspend fun getPokemonRemoteKeyFromName(pokemonName: String): PokemonRemoteKey {
        return pokemonRemoteKeyDao.getPokemonRemoteKeyFromName(pokemonName)
    }

    override suspend fun deleteAllRemoteKey() {
        pokemonRemoteKeyDao.deleteAll()
    }

    override suspend fun saveRemoteKey(pokemonRemoteKey: PokemonRemoteKey) {
        pokemonRemoteKeyDao.save(pokemonRemoteKey)
    }

    override suspend fun saveAllPokemonDetail(pokemonDetails: List<PokemonDetail>) {
        pokemonDetailDao.saveAll(pokemonDetails)
    }

    override suspend fun saveAllPokemonSpecies(species: List<PokemonSpecies>) {
        pokemonSpeciesDao.saveAllSpecie(species)
    }

    override suspend fun saveAllEvolutionChain(evolutionChain: List<EvolutionChain>) {
        evolutionChainDao.saveAll(evolutionChain)
    }

    override suspend fun saveEvolutionChain(evolutionChain: EvolutionChain) {
        evolutionChainDao.save(evolutionChain)
    }

    override fun searchEvolutionChainById(chainId: Int): EvolutionChain? {
        return evolutionChainDao.searchEvolutionChainById(chainId)
    }

}
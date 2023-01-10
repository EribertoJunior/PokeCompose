package br.com.estudos.pokecompose.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.estudos.pokecompose.repository.local.entities.PokemonRemoteKey

@Dao
interface PokemonRemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(pokemonRemoteKey: List<PokemonRemoteKey>)

    @Query("Select * From PokemonRemoteKey Where pokemonName = :pokemonName")
    suspend fun getPokemonRemoteKeyFromName(pokemonName: String): PokemonRemoteKey

    @Query("Delete From PokemonRemoteKey")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(pokemonRemoteKey: PokemonRemoteKey)
}
package br.com.estudos.pokecompose.repository.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.estudos.pokecompose.model.local.Pokemon
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(pokemons: List<Pokemon>)

    @Query("Select * From Pokemon")
    fun getPokemons(): PagingSource<Int, Pokemon>

    @Query("Delete From Pokemon")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(pokemon: Pokemon)

    @Query("Select * From Pokemon Where name = :name")
    fun searchPokemonByName(name: String): Flow<Pokemon>
}
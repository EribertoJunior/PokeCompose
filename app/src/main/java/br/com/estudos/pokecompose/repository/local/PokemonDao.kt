package br.com.estudos.pokecompose.repository.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.estudos.pokecompose.model.local.Pokemon

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(pokemons: List<Pokemon>)

    @Query("Select * From Pokemon")
    fun getPokemons(): PagingSource<Int, Pokemon>

    @Query("Delete From Pokemon")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(pokemon: Pokemon)
}
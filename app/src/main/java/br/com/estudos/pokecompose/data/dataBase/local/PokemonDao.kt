package br.com.estudos.pokecompose.data.dataBase.local

import androidx.paging.PagingSource
import androidx.room.*
import br.com.estudos.pokecompose.data.dataBase.local.entities.Pokemon
import br.com.estudos.pokecompose.data.model.local.PokemonAndDetail
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(pokemons: List<Pokemon>)

    @Transaction
    @Query("Select * From Pokemon order by pokemonId")
    fun getPokemons(): PagingSource<Int, PokemonAndDetail>

    @Query("Delete From Pokemon")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(pokemon: Pokemon)

    @Transaction
    @Query("Select * From Pokemon Where name = :name")
    fun searchPokemonByName(name: String): Flow<PokemonAndDetail>
}
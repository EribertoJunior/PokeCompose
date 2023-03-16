package br.com.estudos.pokecompose.data.dataBase.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import br.com.estudos.pokecompose.data.dataBase.local.entities.PokemonDetail

@Dao
interface PokemonDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(pokemonDetails: List<PokemonDetail>)
}
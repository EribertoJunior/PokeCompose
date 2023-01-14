package br.com.estudos.pokecompose.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import br.com.estudos.pokecompose.repository.local.entities.PokemonSpecies

@Dao
interface PokemonSpeciesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllSpecie(species: List<PokemonSpecies> )
}
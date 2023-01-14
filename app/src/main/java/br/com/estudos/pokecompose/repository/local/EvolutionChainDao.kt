package br.com.estudos.pokecompose.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.estudos.pokecompose.repository.local.entities.EvolutionChain

@Dao
interface EvolutionChainDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(evolutionChain: List<EvolutionChain>)

    @Query("Select * From EvolutionChain Where id = :chainId")
    fun searchEvolutionChainById(chainId: Int): EvolutionChain?
}
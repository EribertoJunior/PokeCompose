package br.com.estudos.pokecompose.repository.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import br.com.estudos.pokecompose.repository.local.converters.ConverterSpecieToEvolution

@Entity
data class EvolutionChain(
    @PrimaryKey val evolutionChainId: Int,
    @TypeConverters(ConverterSpecieToEvolution::class)
    val evolutionList: List<SpecieToEvolution>
){
    companion object{
        const val EVOLUTION_CHAIN_ID = "evolutionChainId"
    }
}

data class SpecieToEvolution(
    val name: String,
    val imageUrl: String,
)

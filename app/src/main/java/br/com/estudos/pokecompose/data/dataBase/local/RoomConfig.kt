package br.com.estudos.pokecompose.data.dataBase.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.estudos.pokecompose.data.dataBase.local.converters.ConverterPokemonDetailStats
import br.com.estudos.pokecompose.data.dataBase.local.converters.ConverterSpecieToEvolution
import br.com.estudos.pokecompose.data.dataBase.local.converters.ConverterTypeColoursEnum
import br.com.estudos.pokecompose.data.dataBase.local.entities.*

@Database(entities = [Pokemon::class, EvolutionChain::class, PokemonDetail::class, PokemonRemoteKey::class, PokemonSpecies::class], version = 1, exportSchema = false)
@TypeConverters(ConverterTypeColoursEnum::class, ConverterPokemonDetailStats::class, ConverterSpecieToEvolution::class)
abstract class RoomConfig : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
    abstract fun pokemonRemoteKeyDao(): PokemonRemoteKeyDao
    abstract fun pokemonDetailDao(): PokemonDetailDao
    abstract fun pokemonSpeciesDao(): PokemonSpeciesDao
    abstract fun pokemonEvolutionChainDao(): EvolutionChainDao

    companion object {
        @Volatile
        private var INSTANCE: RoomConfig? = null

        fun getDataBase(context: Context): RoomConfig {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomConfig::class.java,
                    "pokemon_database"
                ).fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}
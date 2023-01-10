package br.com.estudos.pokecompose.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.estudos.pokecompose.repository.local.converters.ConverterSpecies
import br.com.estudos.pokecompose.repository.local.converters.ConverterTypeColoursEnum
import br.com.estudos.pokecompose.repository.local.entities.Pokemon
import br.com.estudos.pokecompose.repository.local.entities.PokemonDetail
import br.com.estudos.pokecompose.repository.local.entities.PokemonRemoteKey

@Database(entities = [Pokemon::class, PokemonDetail::class, PokemonRemoteKey::class], version = 1, exportSchema = false)
@TypeConverters(ConverterTypeColoursEnum::class, ConverterSpecies::class)
abstract class RoomConfig : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
    abstract fun pokemonRemoteKeyDao(): PokemonRemoteKeyDao
    abstract fun pokemonDetailDao(): PokemonDetailDao

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
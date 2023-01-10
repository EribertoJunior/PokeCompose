package br.com.estudos.pokecompose.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.estudos.pokecompose.model.local.ConverterSpecies
import br.com.estudos.pokecompose.model.local.ConverterTypeColoursEnum
import br.com.estudos.pokecompose.model.local.Pokemon
import br.com.estudos.pokecompose.model.local.PokemonRemoteKey

@Database(entities = [Pokemon::class, PokemonRemoteKey::class], version = 1, exportSchema = false)
@TypeConverters(ConverterTypeColoursEnum::class, ConverterSpecies::class)
abstract class RoomConfig : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
    abstract fun pokemonRemoteKeyDao(): PokemonRemoteKeyDao

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
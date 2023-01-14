package br.com.estudos.pokecompose.repository.local.converters

import androidx.room.TypeConverter
import br.com.estudos.pokecompose.repository.local.entities.SpecieToEvolution
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConverterSpecieToEvolution {
    @TypeConverter
    fun restoreList(typeSpeciesString: String): List<SpecieToEvolution> {
        return Gson().fromJson(
            typeSpeciesString,
            object : TypeToken<List<SpecieToEvolution>>() {}.type
        )
    }

    @TypeConverter
    fun saveListToListString(typeColoursEnums: List<SpecieToEvolution>): String {
        return Gson().toJson(typeColoursEnums)
    }
}
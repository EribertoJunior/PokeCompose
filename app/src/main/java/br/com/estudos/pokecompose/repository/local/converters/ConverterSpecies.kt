package br.com.estudos.pokecompose.repository.local.converters

import androidx.room.TypeConverter
import br.com.estudos.pokecompose.model.local.Species
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConverterSpecies {
    @TypeConverter
    fun restoreList(typeSpeciesString: String): List<Species> {
        return Gson().fromJson(
            typeSpeciesString,
            object : TypeToken<List<Species>>() {}.type
        )
    }

    @TypeConverter
    fun saveListToListString(typeColoursEnums: List<Species>): String {
        return Gson().toJson(typeColoursEnums)
    }
}
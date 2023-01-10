package br.com.estudos.pokecompose.model.local

import androidx.room.TypeConverter
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
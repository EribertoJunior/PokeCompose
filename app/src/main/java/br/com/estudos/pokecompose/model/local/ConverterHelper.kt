package br.com.estudos.pokecompose.model.local

import androidx.room.TypeConverter
import br.com.estudos.pokecompose.model.local.enums.TypeColoursEnum
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConverterHelper {

    @TypeConverter
    fun restoreListEnum(typeColoursEnumNames: String): List<TypeColoursEnum> {
        return Gson().fromJson(
            typeColoursEnumNames,
            object : TypeToken<List<TypeColoursEnum>>() {}.type
        )
    }

    @TypeConverter
    fun saveListEnumToListString(typeColoursEnums: List<TypeColoursEnum>): String {
        return Gson().toJson(typeColoursEnums)
    }
}
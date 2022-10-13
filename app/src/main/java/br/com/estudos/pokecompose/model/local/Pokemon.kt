package br.com.estudos.pokecompose.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import br.com.estudos.pokecompose.model.local.enums.TypeColoursEnum

@Entity
data class Pokemon(
    @PrimaryKey var id: Int,
    var name: String,
    @TypeConverters(ConverterHelper::class)
    var colorTypeList: List<TypeColoursEnum>,
    var imageUrl: String?
)

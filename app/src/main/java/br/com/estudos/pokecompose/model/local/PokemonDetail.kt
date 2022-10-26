package br.com.estudos.pokecompose.model.local

import androidx.room.Embedded
import androidx.room.TypeConverters
import br.com.estudos.pokecompose.model.local.enums.TypeColoursEnum

data class PokemonDetail(
    @TypeConverters(ConverterHelper::class)
    var colorTypeList: List<TypeColoursEnum>,
    @Embedded(prefix = "sprites_") var sprites: Sprites?,
    var weight: Int,
    var height:Int
)

data class Sprites(
    @Embedded(prefix = "other_") var other: Other?
)

data class Other(
    @Embedded(prefix = "official_artwork_") var officialArtwork: OfficialArtwork?,
    @Embedded(prefix = "home_") var home: Home?,
)

data class Home(
    var frontDefault: String?
)

data class OfficialArtwork(
    var frontDefault: String?
)
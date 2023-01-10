package br.com.estudos.pokecompose.model.local

import androidx.room.Embedded
import androidx.room.TypeConverters
import br.com.estudos.pokecompose.model.local.enums.TypeColoursEnum

data class PokemonDetail(
    @TypeConverters(ConverterTypeColoursEnum::class)
    var colorTypeList: List<TypeColoursEnum> = listOf(TypeColoursEnum.DARK),
    @Embedded(prefix = "sprites_") var sprites: Sprites? = null,
    var weight: Int = 0,
    var height:Int = 0,
    @TypeConverters(ConverterSpecies::class)
    var species: List<Species> = emptyList(),
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
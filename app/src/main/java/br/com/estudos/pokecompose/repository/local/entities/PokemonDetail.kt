package br.com.estudos.pokecompose.repository.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import br.com.estudos.pokecompose.model.local.Species
import br.com.estudos.pokecompose.model.local.enums.TypeColoursEnum
import br.com.estudos.pokecompose.repository.local.converters.ConverterSpecies
import br.com.estudos.pokecompose.repository.local.converters.ConverterTypeColoursEnum

@Entity
data class PokemonDetail(
    @PrimaryKey val pokemonDetailId: Int = 0,
    val pokemonOwnerId: Int = 0,
    @TypeConverters(ConverterTypeColoursEnum::class)
    var colorTypeList: List<TypeColoursEnum> = listOf(TypeColoursEnum.DARK),
    @Embedded(prefix = "sprites_") var sprites: Sprites? = null,
    var weight: Int = 0,
    var height: Int = 0,
    @TypeConverters(ConverterSpecies::class)
    var species: List<Species> = emptyList(),
) {
    companion object {
        const val POKEMON_DETAIL_ID = "pokemonDetailId"
        const val POKEMON_DETAIL_OWNER_ID = "pokemonOwnerId"
    }
}

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
package br.com.estudos.pokecompose.repository.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import br.com.estudos.pokecompose.model.local.enums.TypeColoursEnum
import br.com.estudos.pokecompose.repository.local.converters.ConverterPokemonDetailStats
import br.com.estudos.pokecompose.repository.local.converters.ConverterTypeColoursEnum

@Entity
data class PokemonDetail(
    @PrimaryKey
    val pokemonDetailId: Int = 0,
    val pokemonOwnerId: Int = 0,
    val weight: Int = 0,
    val height: Int = 0,
    @Embedded(prefix = "sprites_")
    val sprites: Sprites? = null,
    @TypeConverters(ConverterTypeColoursEnum::class)
    var colorTypeList: List<TypeColoursEnum> = listOf(TypeColoursEnum.DARK),
    @Embedded(prefix = "species_")
    var species: PokemonDetailSpecies? = null,
    @TypeConverters(ConverterPokemonDetailStats::class)
    var stats: List<PokemonDetailStats> =  emptyList()
) {
    companion object {
        const val POKEMON_DETAIL_ID = "pokemonDetailId"
        const val POKEMON_DETAIL_OWNER_ID = "pokemonOwnerId"
    }
}

data class PokemonDetailStats(
    val baseStat: Int,
    val effort: Int,
    val stat: Stat,
)

data class Stat(
    val name: String,
    val url: String
)

data class Sprites(
    @Embedded(prefix = "other_") var other: Other?
)

data class PokemonDetailSpecies(
    val name: String,
    val url: String
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
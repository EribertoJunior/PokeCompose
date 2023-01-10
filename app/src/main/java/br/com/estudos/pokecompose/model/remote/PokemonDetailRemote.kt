package br.com.estudos.pokecompose.model.remote

import br.com.estudos.pokecompose.repository.local.entities.Home
import br.com.estudos.pokecompose.repository.local.entities.OfficialArtwork
import br.com.estudos.pokecompose.repository.local.entities.Other
import br.com.estudos.pokecompose.repository.local.entities.PokemonDetail
import br.com.estudos.pokecompose.repository.local.entities.Sprites
import br.com.estudos.pokecompose.model.local.enums.TypeColoursEnum
import com.google.gson.annotations.SerializedName

data class PokemonDetailRemote(
    @SerializedName("id") val id: Int,
    @SerializedName("types") val types: List<DataTypesRemote>,
    @SerializedName("sprites") val sprites: SpritesRemote,
    @SerializedName("weight") val weight: Int,
    @SerializedName("height") val height: Int,
    //@SerializedName("species") val species: SpeciesRemote,

    ) {
    fun pokeDetailRemoteToPokeDetail() =
        PokemonDetail(
            pokemonDetailId = id,
            pokemonOwnerId = id,
            colorTypeList = types.map { dataTypes ->
                TypeColoursEnum.getTypeFromName(dataTypes.type.name)
            },
            weight = weight,
            height = height,
            sprites = Sprites(
                Other(
                    officialArtwork = OfficialArtwork(sprites.other.officialArtwork.frontDefault),
                    home = Home(sprites.other.home.frontDefault)
                )
            )
        )
}

data class SpritesRemote(
    @SerializedName("other") var other: OtherRemote
)

data class OtherRemote(
    @SerializedName("official-artwork") var officialArtwork: OfficialArtworkRemote,
    @SerializedName("home") var home: HomeRemote,
)

data class HomeRemote(
    @SerializedName("front_default") var frontDefault: String?
)

data class OfficialArtworkRemote(
    @SerializedName("front_default") var frontDefault: String?
)

data class DataTypesRemote(
    @SerializedName("slot") var slot: Int,
    @SerializedName("type") var type: TypeRemote
)

data class TypeRemote(
    @SerializedName("name") var name: String
)

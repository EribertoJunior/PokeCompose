package br.com.estudos.pokecompose.model.api

import br.com.estudos.pokecompose.model.local.Home
import br.com.estudos.pokecompose.model.local.OfficialArtwork
import br.com.estudos.pokecompose.model.local.Other
import br.com.estudos.pokecompose.model.local.PokemonDetail
import br.com.estudos.pokecompose.model.local.Sprites
import br.com.estudos.pokecompose.model.local.enums.TypeColoursEnum
import com.google.gson.annotations.SerializedName

data class PokemonDetailRemote(
    @SerializedName("types") var types: List<DataTypesRemote>,
    @SerializedName("sprites") var sprites: SpritesRemote,
    @SerializedName("weight") var weight: Int,
    @SerializedName("height") var height: Int
) {
    fun pokeDetailRemoteToPokeDetail() =
        PokemonDetail(
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

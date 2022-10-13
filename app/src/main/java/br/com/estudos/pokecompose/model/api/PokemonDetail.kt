package br.com.estudos.pokecompose.model.api

import com.google.gson.annotations.SerializedName

data class PokemonDetail(
    @SerializedName("types") var types: List<DataTypes>,
    @SerializedName("sprites") var sprites: Sprites
)

data class Sprites(
    @SerializedName("other") var other: Other
)

data class Other(
    @SerializedName("official-artwork") var officialArtwork: OfficialArtwork,
    @SerializedName("home") var home: Home,
)

data class Home(
    @SerializedName("front_default") var frontDefault: String?
)

data class OfficialArtwork(
    @SerializedName("front_default") var frontDefault: String?
)

data class DataTypes(
    @SerializedName("slot") var slot: Int,
    @SerializedName("type") var type: Type
)

data class Type(
    @SerializedName("name") var name: String
)

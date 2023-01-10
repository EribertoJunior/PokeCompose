package br.com.estudos.pokecompose.model.remote

import com.google.gson.annotations.SerializedName

data class SpeciesRemote(
    @SerializedName("evolution_chain") val evolutionChainAddress: EvolutionChainAddress,
)

data class EvolutionChainAddress(
    @SerializedName("url") val url: String
)
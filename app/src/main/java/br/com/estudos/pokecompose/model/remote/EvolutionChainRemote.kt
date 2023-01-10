package br.com.estudos.pokecompose.model.remote

import com.google.gson.annotations.SerializedName

data class EvolutionChainRemote(
    @SerializedName("chain") val chain: ChainRemote
)

data class ChainRemote(
    @SerializedName("evolves_to") val evolvesTo: List<ChainRemote>,
    @SerializedName("species") val species: SpecieToEvolution
)

data class SpecieToEvolution(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
)

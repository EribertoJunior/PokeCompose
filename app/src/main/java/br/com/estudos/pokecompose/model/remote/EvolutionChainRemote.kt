package br.com.estudos.pokecompose.model.remote

import br.com.estudos.pokecompose.extensions.getUrlId
import br.com.estudos.pokecompose.repository.local.entities.EvolutionChain
import br.com.estudos.pokecompose.repository.local.entities.SpecieToEvolution
import com.google.gson.annotations.SerializedName

data class EvolutionChainRemote(
    @SerializedName("id") val id: Int,
    @SerializedName("chain") val chain: ChainRemote
) {

    fun mapToEvolutionChain(): EvolutionChain {

        val mutableListSpecieToEvolution: ArrayList<SpecieToEvolution> = arrayListOf()

        chain.species.run {
            mutableListSpecieToEvolution.add(
                SpecieToEvolution(
                    name = name,
                    imageUrl = buildUrlImagem(url.getUrlId)
                )
            )
        }
        mutableListSpecieToEvolution.addAll(buildListOfEvolutions(chain.evolvesTo))

        return EvolutionChain(
            evolutionChainId = id,
            evolutionList = mutableListSpecieToEvolution
        )
    }

    private fun buildListOfEvolutions(chainRemote: List<ChainRemote>): List<SpecieToEvolution> {
        val mutableList = arrayListOf<SpecieToEvolution>()
        if (chainRemote.isNotEmpty()) {
            chainRemote.forEach {
                mutableList.add(
                    SpecieToEvolution(
                        name = it.species.name,
                        imageUrl = buildUrlImagem(it.species.url.getUrlId)
                    ),
                )
                mutableList.addAll(buildListOfEvolutions(it.evolvesTo))
            }
        } else {
            mutableList.addAll(emptyList())
        }

        return mutableList.toList()
    }

    private fun buildUrlImagem(idPokemon: Int) =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$idPokemon.png"
}

data class ChainRemote(
    @SerializedName("evolves_to") val evolvesTo: List<ChainRemote>,
    @SerializedName("species") val species: SpecieToEvolutionRemote
)

data class SpecieToEvolutionRemote(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
)

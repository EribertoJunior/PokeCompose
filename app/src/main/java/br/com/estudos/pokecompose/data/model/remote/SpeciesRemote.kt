package br.com.estudos.pokecompose.data.model.remote

import br.com.estudos.pokecompose.data.dataBase.local.entities.*
import br.com.estudos.pokecompose.extensions.getUrlId
import com.google.gson.annotations.SerializedName

data class SpeciesRemote(
    @SerializedName("id") val id: Int,
    @SerializedName("evolution_chain") val evolutionChainAddressRemote: EvolutionChainAddressRemote?,
    @SerializedName("flavor_text_entries") val flavorTextEntreyRemotes: List<FlavorTextEntreiesRemote>
) {
    fun mapToPokemonSpecie(): PokemonSpecies {
        return PokemonSpecies(
            pokemonSpeciesId = id,
            pokemonOwnerId = id,
            evolutionChainAddress = EvolutionChainAddress(url = evolutionChainAddressRemote?.url),
            flavorTextEntreies = flavorTextEntreyRemotes
                .filter { it.languageRemote.name == "en" }
                .map {
                    FlavorTextEntreies(
                        flavorText = it.flavorText,
                        version = Version(it.versionRemote.name),
                        language = Language(it.languageRemote.name)
                    )

                }
                .first(),
            pokemonSpeciesEvolutionChainId = evolutionChainAddressRemote?.url?.getUrlId ?: 0
        )
    }
}

data class EvolutionChainAddressRemote(
    @SerializedName("url") val url: String
)

data class FlavorTextEntreiesRemote(
    @SerializedName("flavor_text") val flavorText: String,
    @SerializedName("version") val versionRemote: VersionRemote,
    @SerializedName("language") val languageRemote: LanguageRemote
)

data class LanguageRemote(
    @SerializedName("name") val name: String
)

data class VersionRemote(
    @SerializedName("name") val name: String
)
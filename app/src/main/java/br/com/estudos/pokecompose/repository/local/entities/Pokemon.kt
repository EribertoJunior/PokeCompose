package br.com.estudos.pokecompose.repository.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pokemon(
    @PrimaryKey var pokemonId: Int = 0,
    var name: String = "",
    var imageUrl: String? = null,
    //@Embedded(prefix = "detail_") var pokemonDetail: PokemonDetail
) {
    val idFormatted: String
        get() {
            return if (pokemonId < 10) {
                "#00$pokemonId"
            } else if (pokemonId in 10..99) {
                "#0$pokemonId"
            } else {
                "#$pokemonId"
            }
        }

    companion object {
        const val POKEMON_ID = "pokemonId"
    }
}

package br.com.estudos.pokecompose.model.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pokemon(
    @PrimaryKey var id: Int = 0,
    var name: String = "",
    var imageUrl: String? = null,
    @Embedded(prefix = "detail_") var pokemonDetail: PokemonDetail
) {
    val idFormatted: String
        get() {
            return if (id < 10) {
                "#00$id"
            } else if (id in 10..99) {
                "#0$id"
            } else {
                "#$id"
            }
        }
}

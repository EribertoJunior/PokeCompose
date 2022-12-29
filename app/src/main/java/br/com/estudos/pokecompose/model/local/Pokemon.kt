package br.com.estudos.pokecompose.model.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pokemon(
    @PrimaryKey var id: Int,
    var name: String,
    var imageUrl: String?,
    @Embedded(prefix = "detail_") var pokemonDetail: PokemonDetail
)

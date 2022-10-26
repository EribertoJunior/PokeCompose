package br.com.estudos.pokecompose.model.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import br.com.estudos.pokecompose.model.api.PokemonDetailRemote
import br.com.estudos.pokecompose.model.local.enums.TypeColoursEnum

@Entity
data class Pokemon(
    @PrimaryKey var id: Int,
    var name: String,
    var imageUrl: String?,
    @Embedded(prefix = "detail_") var pokemonDetail: PokemonDetail
)

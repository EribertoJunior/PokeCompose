package br.com.estudos.pokecompose.data.dataBase.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonRemoteKey(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var pokemonName: String,
    var prevOffset: Int?,
    var nextOffset: Int?
)
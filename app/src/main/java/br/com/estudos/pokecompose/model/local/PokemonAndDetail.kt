package br.com.estudos.pokecompose.model.local

import androidx.room.Embedded
import androidx.room.Relation
import br.com.estudos.pokecompose.repository.local.entities.Pokemon
import br.com.estudos.pokecompose.repository.local.entities.Pokemon.Companion.POKEMON_ID
import br.com.estudos.pokecompose.repository.local.entities.PokemonDetail
import br.com.estudos.pokecompose.repository.local.entities.PokemonDetail.Companion.POKEMON_DETAIL_OWNER_ID

data class PokemonAndDetail(
    @Embedded val pokemon: Pokemon,
    @Relation(
        parentColumn = POKEMON_ID,
        entityColumn = POKEMON_DETAIL_OWNER_ID
    )
    val pokemonDetail: PokemonDetail
)
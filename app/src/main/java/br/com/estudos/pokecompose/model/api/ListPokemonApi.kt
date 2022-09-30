package br.com.estudos.pokecompose.model.api

data class ListPokemonApi(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonApi>
)

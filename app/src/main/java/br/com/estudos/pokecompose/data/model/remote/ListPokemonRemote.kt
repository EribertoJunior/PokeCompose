package br.com.estudos.pokecompose.data.model.remote

data class ListPokemonRemote(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonRemote>
)

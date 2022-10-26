package br.com.estudos.pokecompose.repository.remote

import br.com.estudos.pokecompose.model.api.ListPokemonApi
import br.com.estudos.pokecompose.model.api.PokemonDetailRemote
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {
    @GET("pokemon")
    suspend fun getListPokemon(
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int
    ): ListPokemonApi

    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(@Path("id") pokemonId: Int) : PokemonDetailRemote
}
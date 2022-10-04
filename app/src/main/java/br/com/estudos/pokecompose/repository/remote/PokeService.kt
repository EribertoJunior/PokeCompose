package br.com.estudos.pokecompose.repository.remote

import br.com.estudos.pokecompose.model.api.ListPokemonApi
import retrofit2.http.GET
import retrofit2.http.Query

interface PokeService {
    @GET("pokemon")
    suspend fun getListPokemon(
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int
    ): ListPokemonApi

    // @GET("pokemon/{idPokemon}")
    // fun getDetalhePokemon(@Path("idPokemon") idPokemon: Int) : Call<RetornoPokemonDetalhe>
}
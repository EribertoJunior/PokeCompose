package br.com.estudos.pokecompose.repository.remote

import br.com.estudos.pokecompose.model.remote.EvolutionChainRemote
import br.com.estudos.pokecompose.model.remote.ListPokemonRemote
import br.com.estudos.pokecompose.model.remote.PokemonDetailRemote
import br.com.estudos.pokecompose.model.remote.SpeciesRemote
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface PokemonService {
    @GET("pokemon")
    suspend fun getListPokemon(
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int
    ): ListPokemonRemote

    @GET("pokemon/{name}")
    suspend fun getPokemonDetails(@Path("name") pokemonName: String) : PokemonDetailRemote

    @GET("pokemon-species/{name}")
    suspend fun searchPokemonSpecieByName(@Path("name") pokemonName: String): SpeciesRemote?

    @GET
    suspend fun searchEvolutionChan(@Url evolutionChanUrl: String): EvolutionChainRemote
}
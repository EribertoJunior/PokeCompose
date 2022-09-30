package br.com.estudos.pokecompose.repository

import br.com.estudos.pokecompose.model.local.Pokemon
import br.com.estudos.pokecompose.samples.listPokemonSample

class RepositoryImpl : Repository {
    override fun getPokemonList(): List<Pokemon> = listPokemonSample
}
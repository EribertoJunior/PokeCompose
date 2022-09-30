package br.com.estudos.pokecompose.repository

import br.com.estudos.pokecompose.model.local.Pokemon

interface Repository {
    fun getPokemonList(): List<Pokemon>
}
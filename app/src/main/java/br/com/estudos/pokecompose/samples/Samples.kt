package br.com.estudos.pokecompose.samples

import androidx.compose.ui.graphics.Color
import br.com.estudos.pokecompose.extensions.color
import br.com.estudos.pokecompose.model.local.Pokemon
import br.com.estudos.pokecompose.model.local.enums.TypeColoursEnum

private fun setUrlImage(idPokemon: Int) =
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${idPokemon}.png"

val listPokemonSample = listOf(
    Pokemon(
        id = 0,
        name = "Bulbasaur",
        colorTypeList = listOf(
            TypeColoursEnum.GRASS.color,
            TypeColoursEnum.POISON.color,
            Color.Transparent
        ),
        imageUrl = setUrlImage(1)
    ),
    Pokemon(
        id = 1,
        name = "Ivysaur",
        colorTypeList = listOf(
            TypeColoursEnum.GRASS.color,
            TypeColoursEnum.POISON.color,
            Color.Transparent
        ),
        imageUrl = setUrlImage(2)
    ),
    Pokemon(
        id = 2,
        name = "Venusaur",
        colorTypeList = listOf(
            TypeColoursEnum.GRASS.color,
            TypeColoursEnum.POISON.color,
            Color.Transparent
        ),
        imageUrl = setUrlImage(3)
    ),
    Pokemon(
        id = 3,
        name = "Charmander",
        colorTypeList = listOf(
            TypeColoursEnum.FIRE.color,
            Color.Transparent
        ),
        imageUrl = setUrlImage(4)
    ),
    Pokemon(
        id = 4,
        name = "Charmeleon",
        colorTypeList = listOf(
            TypeColoursEnum.FIRE.color,
            Color.Transparent
        ),
        imageUrl = setUrlImage(5)
    ),
    Pokemon(
        id = 5,
        name = "Charizard",
        colorTypeList = listOf(
            TypeColoursEnum.FIRE.color,
            TypeColoursEnum.FLYING.color,
            Color.Transparent
        ),
        imageUrl = setUrlImage(6)
    ),
    Pokemon(
        id = 6,
        name = "Squirtle",
        colorTypeList = listOf(
            TypeColoursEnum.WATER.color,
            Color.Transparent
        ),
        imageUrl = setUrlImage(7)
    ),
    Pokemon(
        id = 7,
        name = "Wartortle",
        colorTypeList = listOf(
            TypeColoursEnum.WATER.color,
            Color.Transparent
        ),
        imageUrl = setUrlImage(8)
    ),
    Pokemon(
        id = 8,
        name = "Blastoise",
        colorTypeList = listOf(
            TypeColoursEnum.WATER.color,
            Color.Transparent
        ),
        imageUrl = setUrlImage(9)
    ),
)

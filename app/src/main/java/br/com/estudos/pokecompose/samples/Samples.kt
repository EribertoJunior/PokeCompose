package br.com.estudos.pokecompose.samples

import br.com.estudos.pokecompose.model.local.Home
import br.com.estudos.pokecompose.model.local.OfficialArtwork
import br.com.estudos.pokecompose.model.local.Other
import br.com.estudos.pokecompose.model.local.Pokemon
import br.com.estudos.pokecompose.model.local.PokemonDetail
import br.com.estudos.pokecompose.model.local.Sprites
import br.com.estudos.pokecompose.model.local.enums.TypeColoursEnum

private fun setUrlImage(idPokemon: Int) =
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${idPokemon}.png"

val listPokemonSample = listOf(
    Pokemon(
        id = 0,
        name = "Bulbasaur",
        pokemonDetail = PokemonDetail(
            colorTypeList = listOf(
                TypeColoursEnum.DRAGON,
                TypeColoursEnum.FIRE
            ),
            sprites = Sprites(
                Other(
                    officialArtwork = OfficialArtwork(""),
                    home = Home("")
                )
            ),
            weight = 123,
            height = 123,
        ),
        imageUrl = setUrlImage(1)
    ),
    Pokemon(
        id = 1,
        name = "Ivysaur",
        pokemonDetail = PokemonDetail(
            colorTypeList = listOf(
                TypeColoursEnum.DRAGON,
                TypeColoursEnum.FIRE
            ),
            sprites = Sprites(
                Other(
                    officialArtwork = OfficialArtwork(""),
                    home = Home("")
                )
            ),
            weight = 123,
            height = 123,
        ),
        imageUrl = setUrlImage(2)
    ),
    Pokemon(
        id = 2,
        name = "Venusaur",
        pokemonDetail = PokemonDetail(
            colorTypeList = listOf(
                TypeColoursEnum.GRASS,
            ),
            sprites = Sprites(
                Other(
                    officialArtwork = OfficialArtwork(""),
                    home = Home("")
                )
            ),
            weight = 123,
            height = 123,
        ),
        imageUrl = setUrlImage(3)
    ),
    Pokemon(
        id = 3,
        name = "Charmander",
        pokemonDetail = PokemonDetail(
            colorTypeList = listOf(
                TypeColoursEnum.DRAGON,
                TypeColoursEnum.FIRE
            ),
            sprites = Sprites(
                Other(
                    officialArtwork = OfficialArtwork(""),
                    home = Home("")
                )
            ),
            weight = 123,
            height = 123,
        ),
        imageUrl = setUrlImage(4)
    ),
    Pokemon(
        id = 4,
        name = "Charmeleon",
        pokemonDetail = PokemonDetail(
            colorTypeList = listOf(
                TypeColoursEnum.DRAGON,
                TypeColoursEnum.FIRE
            ),
            sprites = Sprites(
                Other(
                    officialArtwork = OfficialArtwork(""),
                    home = Home("")
                )
            ),
            weight = 123,
            height = 123,
        ),
        imageUrl = setUrlImage(5)
    ),
    Pokemon(
        id = 5,
        name = "Charizard",
        pokemonDetail = PokemonDetail(
            colorTypeList = listOf(
                TypeColoursEnum.DRAGON,
                TypeColoursEnum.FIRE
            ),
            sprites = Sprites(
                Other(
                    officialArtwork = OfficialArtwork(""),
                    home = Home("")
                )
            ),
            weight = 123,
            height = 123,
        ),
        imageUrl = setUrlImage(6)
    ),
    Pokemon(
        id = 6,
        name = "Squirtle",
        pokemonDetail = PokemonDetail(
            colorTypeList = listOf(
                TypeColoursEnum.DRAGON,
                TypeColoursEnum.FIRE
            ),
            sprites = Sprites(
                Other(
                    officialArtwork = OfficialArtwork(""),
                    home = Home("")
                )
            ),
            weight = 123,
            height = 123,
        ),
        imageUrl = setUrlImage(7)
    ),
    Pokemon(
        id = 7,
        name = "Wartortle",
        pokemonDetail = PokemonDetail(
            colorTypeList = listOf(
                TypeColoursEnum.DRAGON,
                TypeColoursEnum.FIRE
            ),
            sprites = Sprites(
                Other(
                    officialArtwork = OfficialArtwork(""),
                    home = Home("")
                )
            ),
            weight = 123,
            height = 123,
        ),
        imageUrl = setUrlImage(8)
    ),
    Pokemon(
        id = 8,
        name = "Blastoise",
        pokemonDetail = PokemonDetail(
            colorTypeList = listOf(
                TypeColoursEnum.DRAGON,
                TypeColoursEnum.FIRE
            ),
            sprites = Sprites(
                Other(
                    officialArtwork = OfficialArtwork(""),
                    home = Home("")
                )
            ),
            weight = 123,
            height = 123,
        ),
        imageUrl = setUrlImage(9)
    )
)

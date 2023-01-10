package br.com.estudos.pokecompose.samples

import br.com.estudos.pokecompose.model.local.PokemonAndDetail
import br.com.estudos.pokecompose.repository.local.entities.Home
import br.com.estudos.pokecompose.repository.local.entities.OfficialArtwork
import br.com.estudos.pokecompose.repository.local.entities.Other
import br.com.estudos.pokecompose.repository.local.entities.Pokemon
import br.com.estudos.pokecompose.repository.local.entities.PokemonDetail
import br.com.estudos.pokecompose.repository.local.entities.Sprites
import br.com.estudos.pokecompose.model.local.enums.TypeColoursEnum

private fun setUrlImage(idPokemon: Int) =
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${idPokemon}.png"

val listPokemonSample = listOf(
    PokemonAndDetail(
        pokemon = Pokemon(
            pokemonId = 0,
            name = "Bulbasaur",
            imageUrl = setUrlImage(1)
        ),
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
    ),

    PokemonAndDetail(
        pokemon = Pokemon(
            pokemonId = 1,
            name = "Ivysaur",
            imageUrl = setUrlImage(2)
        ),
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
        )
    ),
    PokemonAndDetail(
        pokemon = Pokemon(
            pokemonId = 2,
            name = "Venusaur",
            imageUrl = setUrlImage(3)
        ),
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
        )
    ),
    PokemonAndDetail(
        pokemon = Pokemon(
            pokemonId = 3,
            name = "Charmander",
            imageUrl = setUrlImage(4)
        ),
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
    ),
    PokemonAndDetail(
        pokemon = Pokemon(
            pokemonId = 4,
            name = "Charmeleon",

            imageUrl = setUrlImage(5)
        ),
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
        )
    ),
    PokemonAndDetail(
        pokemon = Pokemon(
            pokemonId = 5,
            name = "Charizard",
            imageUrl = setUrlImage(6)
        ),
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
        )
    ),
    PokemonAndDetail(
        pokemon = Pokemon(
            pokemonId = 6,
            name = "Squirtle",
            imageUrl = setUrlImage(7)
        ),
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
    ),
    PokemonAndDetail(
        pokemon = Pokemon(
            pokemonId = 7,
            name = "Wartortle",
            imageUrl = setUrlImage(8)
        ),
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
    ),
    PokemonAndDetail(
        pokemon = Pokemon(
            pokemonId = 8,
            name = "Blastoise",
            imageUrl = setUrlImage(9)
        ),
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
    )
)

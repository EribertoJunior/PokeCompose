package br.com.estudos.pokecompose.samples

import br.com.estudos.pokecompose.data.dataBase.local.entities.*
import br.com.estudos.pokecompose.data.model.local.PokemonAndDetail
import br.com.estudos.pokecompose.data.model.local.SpecieAndEvolutionChain
import br.com.estudos.pokecompose.data.model.local.enums.TypeColoursEnum

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
            stats = listOf(
                PokemonDetailStats(
                    baseStat = 65,
                    effort = 0,
                    stat = Stat(
                        name = "hp",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 105,
                    effort = 2,
                    stat = Stat(
                        name = "attack",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 60,
                    effort = 0,
                    stat = Stat(
                        name = "defense",
                        url = ""
                    )
                ),
            ),
            species = PokemonDetailSpecies(
                name = "",
                url = ""
            )
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
            stats = listOf(
                PokemonDetailStats(
                    baseStat = 65,
                    effort = 0,
                    stat = Stat(
                        name = "hp",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 105,
                    effort = 2,
                    stat = Stat(
                        name = "attack",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 60,
                    effort = 0,
                    stat = Stat(
                        name = "defense",
                        url = ""
                    )
                ),
            ),
            species = PokemonDetailSpecies(
                name = "",
                url = ""
            )
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
                TypeColoursEnum.DRAGON,
                TypeColoursEnum.FIRE,
            ),
            sprites = Sprites(
                Other(
                    officialArtwork = OfficialArtwork(""),
                    home = Home("")
                )
            ),
            weight = 123,
            height = 123,
            stats = listOf(
                PokemonDetailStats(
                    baseStat = 65,
                    effort = 0,
                    stat = Stat(
                        name = "hp",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 105,
                    effort = 2,
                    stat = Stat(
                        name = "attack",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 60,
                    effort = 0,
                    stat = Stat(
                        name = "defense",
                        url = ""
                    )
                ),
            ),
            species = PokemonDetailSpecies(
                name = "",
                url = ""
            )
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
            stats = listOf(
                PokemonDetailStats(
                    baseStat = 65,
                    effort = 0,
                    stat = Stat(
                        name = "hp",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 105,
                    effort = 2,
                    stat = Stat(
                        name = "attack",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 60,
                    effort = 0,
                    stat = Stat(
                        name = "defense",
                        url = ""
                    )
                ),
            ),
            species = PokemonDetailSpecies(
                name = "",
                url = ""
            )
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
            stats = listOf(
                PokemonDetailStats(
                    baseStat = 65,
                    effort = 0,
                    stat = Stat(
                        name = "hp",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 105,
                    effort = 2,
                    stat = Stat(
                        name = "attack",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 60,
                    effort = 0,
                    stat = Stat(
                        name = "defense",
                        url = ""
                    )
                ),
            ),
            species = PokemonDetailSpecies(
                name = "",
                url = ""
            )
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
            stats = listOf(
                PokemonDetailStats(
                    baseStat = 78,
                    effort = 0,
                    stat = Stat(
                        name = "hp",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 84,
                    effort = 2,
                    stat = Stat(
                        name = "attack",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 78,
                    effort = 0,
                    stat = Stat(
                        name = "defense",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 109,
                    effort = 0,
                    stat = Stat(
                        name = "special-attack",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 85,
                    effort = 0,
                    stat = Stat(
                        name = "special-defense",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 100,
                    effort = 0,
                    stat = Stat(
                        name = "speed",
                        url = ""
                    )
                ),
            ),
            species = PokemonDetailSpecies(
                name = "",
                url = ""
            )
        ),
        specieAndEvolutionChain = SpecieAndEvolutionChain(
            pokemonSpecies = PokemonSpecies(
                flavorTextEntreies = FlavorTextEntreies(
                    flavorText = "A CHARIZARD flies about in search of\nstrong opponents. It breathes intense\nflames that can melt any material. However,  \nit will never torch a weaker foe.",
                    version = Version("en"),
                    language = Language("en")
                ),
                evolutionChainAddress = EvolutionChainAddress(""),
                pokemonSpeciesEvolutionChainId = 0
            ),
            evolutionChain = EvolutionChain(
                evolutionChainId = 0,
                evolutionList = listOf(
                    SpecieToEvolution(name = "charmander", imageUrl = ""),
                    SpecieToEvolution(name = "charmeleon", imageUrl = ""),
                    SpecieToEvolution(name = "charizard", imageUrl = ""),
                )
            )
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
            stats = listOf(
                PokemonDetailStats(
                    baseStat = 65,
                    effort = 0,
                    stat = Stat(
                        name = "hp",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 105,
                    effort = 2,
                    stat = Stat(
                        name = "attack",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 60,
                    effort = 0,
                    stat = Stat(
                        name = "defense",
                        url = ""
                    )
                ),
            ),
            species = PokemonDetailSpecies(
                name = "",
                url = ""
            )
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
            stats = listOf(
                PokemonDetailStats(
                    baseStat = 65,
                    effort = 0,
                    stat = Stat(
                        name = "hp",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 105,
                    effort = 2,
                    stat = Stat(
                        name = "attack",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 60,
                    effort = 0,
                    stat = Stat(
                        name = "defense",
                        url = ""
                    )
                ),
            ),
            species = PokemonDetailSpecies(
                name = "",
                url = ""
            )
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
            stats = listOf(
                PokemonDetailStats(
                    baseStat = 65,
                    effort = 0,
                    stat = Stat(
                        name = "hp",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 105,
                    effort = 2,
                    stat = Stat(
                        name = "attack",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 60,
                    effort = 0,
                    stat = Stat(
                        name = "defense",
                        url = ""
                    )
                ),
            ),
            species = PokemonDetailSpecies(
                name = "",
                url = ""
            )
        ),
    )
)

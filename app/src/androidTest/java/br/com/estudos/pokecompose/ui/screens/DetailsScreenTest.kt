package br.com.estudos.pokecompose.ui.screens

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import br.com.estudos.pokecompose.data.dataBase.local.entities.*
import br.com.estudos.pokecompose.data.model.local.PokemonAndDetail
import br.com.estudos.pokecompose.data.model.local.SpecieAndEvolutionChain
import br.com.estudos.pokecompose.data.model.local.enums.TypeColoursEnum
import org.junit.Rule
import org.junit.Test

class DetailsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun pokemonAndDetail() = run {
        PokemonAndDetail(
            pokemon = Pokemon(
                pokemonId = 5,
                name = "Charizard",
                imageUrl = "/6.png"
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

        )
    }


    @Test
    fun detailsScreenTest_pokemonNameExists() {
        composeTestRule.setContent {
            DetailsScreen(pokemonAndDetail = pokemonAndDetail())
        }

        composeTestRule.onRoot().printToLog("detailsScreenTest")

        composeTestRule
            .onNodeWithText("Charizard")
            .assertExists()
    }

    @Test
    fun detailsScreenTest_pokemonIdExists() {
        composeTestRule.setContent {
            DetailsScreen(pokemonAndDetail = pokemonAndDetail())
        }

        composeTestRule.onRoot().printToLog("detailsScreenTest")

        composeTestRule
            .onNodeWithText("#005")
            .assertExists()
    }

    @Test
    fun detailsScreenTest_pokemonDescriptionExists() {
        composeTestRule.setContent {
            DetailsScreen(pokemonAndDetail = pokemonAndDetail())
        }

        composeTestRule.onRoot().printToLog("detailsScreenTest")

        composeTestRule
            .onNodeWithText(
                "A CHARIZARD flies about in search of\n" +
                        "strong opponents. It breathes intense\n" +
                        "flames that can melt any material. However,  \n" +
                        "it will never torch a weaker foe."
            )
            .assertExists()
    }

    @Test
    fun detailsScreenTest_baseStatsTextExists() {
        composeTestRule.setContent {
            DetailsScreen(pokemonAndDetail = pokemonAndDetail())
        }

        composeTestRule.onRoot().printToLog("detailsScreenTest")

        composeTestRule
            .onNodeWithText("Base stats")
            .assertExists()
    }

    @Test
    fun detailsScreenTest_hpTextExists() {
        composeTestRule.setContent {
            DetailsScreen(pokemonAndDetail = pokemonAndDetail())
        }

        composeTestRule.onRoot().printToLog("detailsScreenTest")

        composeTestRule
            .onNodeWithText("Hp")
            .assertExists()
    }

    @Test
    fun detailsScreenTest_hpProgressBarExists() {
        composeTestRule.setContent {
            DetailsScreen(pokemonAndDetail = pokemonAndDetail())
        }

        composeTestRule.onRoot().printToLog("detailsScreenTest")

        composeTestRule
            .onNodeWithContentDescription("hp progress bar")
            .assertExists()
    }

    @Test
    fun detailsScreenTest_hpProgressBarValueExists() {
        composeTestRule.setContent {
            DetailsScreen(pokemonAndDetail = pokemonAndDetail())
        }

        composeTestRule.onRoot().printToLog("detailsScreenTest")

        composeTestRule
            .onNode(
                hasText("78") and
                        hasParent(
                            hasContentDescription("hp progress bar")
                        ),
                useUnmergedTree = true
            )
            .assertExists()
    }

    @Test
    fun detailsScreenTest_attackTextExists() {
        composeTestRule.setContent {
            DetailsScreen(pokemonAndDetail = pokemonAndDetail())
        }

        composeTestRule.onRoot().printToLog("detailsScreenTest")

        composeTestRule
            .onNodeWithText("Attack")
            .assertExists()
    }

    @Test
    fun detailsScreenTest_attackProgressBarExists() {
        composeTestRule.setContent {
            DetailsScreen(pokemonAndDetail = pokemonAndDetail())
        }

        composeTestRule.onRoot().printToLog("detailsScreenTest")

        composeTestRule
            .onNodeWithContentDescription("attack progress bar")
            .assertExists()
    }

    @Test
    fun detailsScreenTest_attackProgressBarValueExists() {
        composeTestRule.setContent {
            DetailsScreen(pokemonAndDetail = pokemonAndDetail())
        }

        composeTestRule.onRoot().printToLog("detailsScreenTest")

        composeTestRule
            .onNode(
                hasText("84") and
                        hasParent(
                            hasContentDescription("attack progress bar")
                        ),
                useUnmergedTree = true
            )
            .assertExists()
    }

    @Test
    fun detailsScreenTest_defenseTextExists() {
        composeTestRule.setContent {
            DetailsScreen(pokemonAndDetail = pokemonAndDetail())
        }

        composeTestRule.onRoot().printToLog("detailsScreenTest")

        composeTestRule
            .onNodeWithText("Defense")
            .assertExists()
    }

    @Test
    fun detailsScreenTest_defenseProgressBarExists() {
        composeTestRule.setContent {
            DetailsScreen(pokemonAndDetail = pokemonAndDetail())
        }

        composeTestRule.onRoot().printToLog("detailsScreenTest")

        composeTestRule
            .onNodeWithContentDescription("defense progress bar")
            .assertExists()
    }

    @Test
    fun detailsScreenTest_defenseProgressBarValueExists() {
        composeTestRule.setContent {
            DetailsScreen(pokemonAndDetail = pokemonAndDetail())
        }

        composeTestRule.onRoot().printToLog("detailsScreenTest")

        composeTestRule
            .onNode(
                hasText("78") and
                        hasParent(
                            hasContentDescription("defense progress bar")
                        ),
                useUnmergedTree = true
            )
            .assertExists()
    }

    @Test
    fun detailsScreenTest_specialAttackTextExists() {
        composeTestRule.setContent {
            DetailsScreen(pokemonAndDetail = pokemonAndDetail())
        }

        composeTestRule.onRoot().printToLog("detailsScreenTest")

        composeTestRule
            .onNodeWithText("Special-attack")
            .assertExists()
    }

    @Test
    fun detailsScreenTest_specialAttackProgressBarExists() {
        composeTestRule.setContent {
            DetailsScreen(pokemonAndDetail = pokemonAndDetail())
        }

        composeTestRule.onRoot().printToLog("detailsScreenTest")

        composeTestRule
            .onNodeWithContentDescription("special-attack progress bar")
            .assertExists()
    }

    @Test
    fun detailsScreenTest_specialAttackProgressBarValueExists() {
        composeTestRule.setContent {
            DetailsScreen(pokemonAndDetail = pokemonAndDetail())
        }

        composeTestRule.onRoot().printToLog("detailsScreenTest")

        composeTestRule
            .onNode(
                hasText("109") and
                        hasParent(
                            hasContentDescription("special-attack progress bar")
                        ),
                useUnmergedTree = true
            )
            .assertExists()
    }

    @Test
    fun detailsScreenTest_specialDefenseTextExists() {
        composeTestRule.setContent {
            DetailsScreen(pokemonAndDetail = pokemonAndDetail())
        }

        composeTestRule.onRoot().printToLog("detailsScreenTest")

        composeTestRule
            .onNodeWithText("Special-defense")
            .assertExists()
    }

    @Test
    fun detailsScreenTest_specialDefenseProgressBarExists() {
        composeTestRule.setContent {
            DetailsScreen(pokemonAndDetail = pokemonAndDetail())
        }

        composeTestRule.onRoot().printToLog("detailsScreenTest")

        composeTestRule
            .onNodeWithContentDescription("special-defense progress bar")
            .assertExists()
    }

    @Test
    fun detailsScreenTest_specialDefenseProgressBarValueExists() {
        composeTestRule.setContent {
            DetailsScreen(pokemonAndDetail = pokemonAndDetail())
        }

        composeTestRule.onRoot().printToLog("detailsScreenTest")

        composeTestRule
            .onNode(
                hasText("85") and
                        hasParent(
                            hasContentDescription("special-defense progress bar")
                        ),
                useUnmergedTree = true
            )
            .assertExists()
    }

    @Test
    fun detailsScreenTest_speedTextExists() {
        composeTestRule.setContent {
            DetailsScreen(pokemonAndDetail = pokemonAndDetail())
        }

        composeTestRule.onRoot().printToLog("detailsScreenTest")

        composeTestRule
            .onNodeWithText("Speed")
            .assertExists()
    }

    @Test
    fun detailsScreenTest_speedProgressBarExists() {
        composeTestRule.setContent {
            DetailsScreen(pokemonAndDetail = pokemonAndDetail())
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("detailsScreenTest")

        composeTestRule
            .onNodeWithContentDescription("speed progress bar")
            .assertExists()
    }

    @Test
    fun detailsScreenTest_speedProgressBarValueExists() {
        composeTestRule.setContent {
            DetailsScreen(pokemonAndDetail = pokemonAndDetail())
        }

        composeTestRule.onRoot().printToLog("detailsScreenTest")

        composeTestRule
            .onNode(
                hasText("100") and
                        hasParent(
                            hasContentDescription("speed progress bar")
                        ),
                useUnmergedTree = true
            )
            .assertExists()
    }
}

package br.com.estudos.pokecompose.ui.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import org.junit.Rule
import org.junit.Test

class LoadingItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loadingItemTest() {
        composeTestRule.setContent {
            LoadingItem()
        }

        composeTestRule
            .onNodeWithContentDescription("Loading Pokemons")
    }
}
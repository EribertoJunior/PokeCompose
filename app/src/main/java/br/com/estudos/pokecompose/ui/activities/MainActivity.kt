package br.com.estudos.pokecompose.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import br.com.estudos.pokecompose.ui.screens.HomeScreen
import br.com.estudos.pokecompose.ui.theme.PokeComposeTheme
import br.com.estudos.pokecompose.viewmodels.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(
                content = {
                    HomeScreen(viewModel = viewModel, onClickPokemon = {

                    })
                }
            )
        }
    }
}

@Composable
fun App(content: @Composable () -> Unit = {}) {
    PokeComposeTheme {
        Surface {
            content()
        }
    }
}

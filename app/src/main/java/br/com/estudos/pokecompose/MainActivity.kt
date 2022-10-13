package br.com.estudos.pokecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.estudos.pokecompose.ui.components.PokemonList
import br.com.estudos.pokecompose.ui.theme.PokeComposeTheme
import br.com.estudos.pokecompose.viewmodel.HomeViewModel
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun App(viewModel: HomeViewModel = getViewModel()) {
    PokeComposeTheme {
        Surface {
            PokemonList(pokemonList = viewModel.stateFlow.collectAsLazyPagingItems())
        }
    }
}

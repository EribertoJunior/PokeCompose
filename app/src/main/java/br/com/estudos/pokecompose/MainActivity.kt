package br.com.estudos.pokecompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import br.com.estudos.pokecompose.ui.components.PokemonList
import br.com.estudos.pokecompose.ui.theme.PokeComposeTheme
import br.com.estudos.pokecompose.viewmodel.HomeUiState
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
            when(val state = viewModel.homeState.collectAsState().value){
                HomeUiState.Empy -> {
                    Toast.makeText(LocalContext.current, "Lista vazia", Toast.LENGTH_SHORT).show()
                }
                is HomeUiState.Error -> {
                    Toast.makeText(LocalContext.current, state.message, Toast.LENGTH_SHORT).show()
                }
                is HomeUiState.Loaded -> {
                    PokemonList(pokemonList = state.data)
                }
                HomeUiState.Loading -> {
                    Toast.makeText(LocalContext.current, "Carregando", Toast.LENGTH_SHORT).show()
                }
            }
            //PokemonItem()
        }
    }
}

package br.com.estudos.pokecompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
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
            PokemonList(pokemonList = viewModel.fetchPokemons().collectAsLazyPagingItems())
        }
    }
}

package br.com.estudos.pokecompose.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import br.com.estudos.pokecompose.ui.screens.DetailsScreen
import br.com.estudos.pokecompose.ui.theme.PokeComposeTheme
import br.com.estudos.pokecompose.viewmodels.DetailsViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : ComponentActivity() {

    private val viewModel: DetailsViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokeComposeTheme {
                Surface {
                    val stringExtra = intent.getStringExtra(DETAILS_ACTIVITY_POKEMON_NAME).orEmpty()
                    //Toast.makeText(this, stringExtra, Toast.LENGTH_SHORT).show()

                    DetailsScreen(stringExtra, getViewModel())
                }
            }
        }
    }

    companion object {
        const val DETAILS_ACTIVITY_POKEMON_NAME = "pokemonName"
    }
}
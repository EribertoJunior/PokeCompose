package br.com.estudos.pokecompose.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import br.com.estudos.pokecompose.ui.theme.PokeComposeTheme

class PokemonDetailsActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokeComposeTheme {
                Surface {
                    val intExtra = intent.getIntExtra("pokemonId", 0)
                    Toast.makeText(this,intExtra.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
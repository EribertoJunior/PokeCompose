package br.com.estudos.pokecompose.ui.components

import android.content.res.Configuration
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import br.com.estudos.pokecompose.ui.theme.PokeComposeTheme

@Composable
fun PokemonNumber(modifier: Modifier = Modifier, pokemonId: Int) {

    Text(
        text = "N°${pokemonId}",
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true,)
@Composable
fun PokemonNumberPreview() {
    PokeComposeTheme {
        Surface {
            PokemonNumber(pokemonId = 1)
        }
    }
}
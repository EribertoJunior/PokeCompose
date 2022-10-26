package br.com.estudos.pokecompose.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.estudos.pokecompose.R
import br.com.estudos.pokecompose.extensions.toDoubleFormat
import br.com.estudos.pokecompose.ui.theme.PokeComposeTheme

@Composable
fun PokemonMeasure(
    formattedMeasure: String,
    @DrawableRes iconId: Int,
    @StringRes iconDescription: Int,
    @StringRes iconContentDescription: Int,
) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Image(
                painter = painterResource(id = iconId),
                contentDescription = stringResource(iconContentDescription),
                modifier = Modifier.size(24.dp)
            )
            Text(text = formattedMeasure, fontWeight = FontWeight.Bold)
        }
        Text(text = stringResource(iconDescription))
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PokemonHeightPreview() {
    PokeComposeTheme {
        Surface {
            PokemonMeasure(
                formattedMeasure = 253.toDoubleFormat(2),
                iconId = R.drawable.ruler_square,
                iconDescription = R.string.height,
                iconContentDescription = R.string.pokemon_height_image_description
            )
        }
    }
}
package br.com.estudos.pokecompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.estudos.pokecompose.R

@Composable
fun LoadingItem(rotationLoading: Float = 0f) {
    // CircularProgressIndicator(
    //     modifier = Modifier
    //         .width(42.dp)
    //         .height(42.dp)
    //         .padding(8.dp),
    //     strokeWidth = 5.dp
    // )

    Image(
        painter = painterResource(id = R.drawable.pokebola),
        contentDescription = null,
        modifier = Modifier
            .rotate(rotationLoading)
            .size(64.dp)
            .padding(8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun LoadingItemPreview() {
    LoadingItem()
}
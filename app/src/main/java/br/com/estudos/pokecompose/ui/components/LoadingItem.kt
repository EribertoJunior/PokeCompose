package br.com.estudos.pokecompose.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoadingItem() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .wrapContentHeight(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(42.dp)
                .height(42.dp)
                .padding(8.dp),
            strokeWidth = 5.dp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingItemPreview() {
    LoadingItem()
}
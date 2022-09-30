package br.com.estudos.pokecompose.extensions

import androidx.compose.ui.graphics.Color

val String.color
    get() = Color(android.graphics.Color.parseColor(this))

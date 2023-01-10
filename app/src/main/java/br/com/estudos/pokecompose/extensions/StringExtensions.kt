package br.com.estudos.pokecompose.extensions

import androidx.compose.ui.graphics.Color
import java.util.Locale

val String.color
    get() = Color(android.graphics.Color.parseColor(this))

val String.getPokeId
    get() = run {
        val regex = "/\\d+".toRegex()
        regex.find(this)?.value?.replace("/", "")?.toInt() ?: 0
    }

val String.titlecase
    get() = run {
        replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }

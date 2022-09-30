package br.com.estudos.pokecompose.model.local

import androidx.compose.ui.graphics.Color

data class Pokemon(
    val id: Int,
    val name: String,
    val colorTypeList: List<Color>,
    val imageUrl:String
)

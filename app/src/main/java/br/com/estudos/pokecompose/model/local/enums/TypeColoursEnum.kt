package br.com.estudos.pokecompose.model.local.enums

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.toUpperCase
import br.com.estudos.pokecompose.extensions.color
import java.util.Locale

enum class TypeColoursEnum(val codColor: String) {
    NORMAL("#A8A77A"),
    FIRE("#EE8130"),
    WATER("#6390F0"),
    ELECTRIC("#F7D02C"),
    GRASS("#7AC74C"),
    ICE("#96D9D6"),
    FIGHTING("#C22E28"),
    POISON("#A33EA1"),
    GROUND("#E2BF65"),
    FLYING("#A98FF3"),
    PSYCHIC("#F95587"),
    BUG("#A6B91A"),
    ROCK("#B6A136"),
    GHOST("#735797"),
    DRAGON("#6F35FC"),
    DARK("#705746"),
    STEEL("#B7B7CE"),
    FAIRY("#D685AD");

    companion object {
        fun getTypeFromName(name: String) = values().first { it.name == name.uppercase(Locale.getDefault()) }
    }
}
package com.duitddu.app.mvi.pokedex.ui.utils

import com.duitddu.app.mvi.pokedex.model.PokemonStatModel

fun PokemonStatModel.getStatName(): String =
    when(name) {
        "hp" -> "HP"
        "attack" -> "ATK"
        "defense" -> "DEF"
        "special-attack" -> "SP ATK"
        "special-defense" -> "SP DEF"
        "speed" -> "SPD"
        else -> ""
    }
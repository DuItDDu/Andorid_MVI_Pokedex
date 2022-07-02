package com.duitddu.app.mvi.pokedex.ui.utils

import androidx.compose.ui.graphics.Color
import com.duitddu.app.mvi.pokedex.model.PokemonType
import com.duitddu.app.mvi.pokedex.ui.theme.*

fun PokemonType.getColor(): Color =
    when (this) {
        PokemonType.NORMAL -> gray500
        PokemonType.FIRE -> red500
        PokemonType.WATER -> blue500
        PokemonType.ELECTRIC -> yellow500
        PokemonType.GRASS -> green500
        PokemonType.ICE -> blue300
        PokemonType.FIGHTING -> red400
        PokemonType.POISON -> purple400
        PokemonType.GROUND -> amber700
        PokemonType.FLYING -> gray600
        PokemonType.PSYCHIC -> purple500
        PokemonType.BUG -> teal
        PokemonType.ROCK -> brown500
        PokemonType.GHOST -> purple600
        PokemonType.DRAGON -> orange500
        PokemonType.DARK -> gray600
        PokemonType.STEEL -> blueGray400
        PokemonType.FAIRY -> pink400
    }
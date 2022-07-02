package com.duitddu.app.mvi.pokedex.ui.list

import com.duitddu.app.mvi.pokedex.model.PokemonModel
import com.duitddu.app.mvi.pokedex.mvi.UiSideEffect

sealed class PokemonListSideEffect : UiSideEffect {
    data class NavigateDetail(val pokemon: PokemonModel) : PokemonListSideEffect()
}
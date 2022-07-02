package com.duitddu.app.mvi.pokedex.ui.list

import com.duitddu.app.mvi.pokedex.model.PokemonModel
import com.duitddu.app.mvi.pokedex.mvi.UiEvent

sealed class PokemonListEvent : UiEvent {
    data class SelectPokemon(val pokemon: PokemonModel) : PokemonListEvent()
}
package com.duitddu.app.mvi.pokedex.ui.detail

import com.duitddu.app.mvi.pokedex.model.PokemonDetailModel
import com.duitddu.app.mvi.pokedex.mvi.UiState

sealed class PokemonDetailState : UiState {
    object Loading : PokemonDetailState()
    data class Loaded(val pokemon: PokemonDetailModel) : PokemonDetailState()
    data class Error(val throwable: Throwable) : PokemonDetailState()
}
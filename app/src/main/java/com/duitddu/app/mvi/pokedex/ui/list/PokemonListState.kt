package com.duitddu.app.mvi.pokedex.ui.list

import androidx.paging.PagingData
import com.duitddu.app.mvi.pokedex.model.PokemonModel
import com.duitddu.app.mvi.pokedex.mvi.UiState
import kotlinx.coroutines.flow.Flow

sealed class PokemonListState : UiState {
    object Loading : PokemonListState()
    data class Loaded(val pokemonPaging: Flow<PagingData<PokemonModel>>) : PokemonListState()
    data class Error(val throwable: Throwable): PokemonListState()
}
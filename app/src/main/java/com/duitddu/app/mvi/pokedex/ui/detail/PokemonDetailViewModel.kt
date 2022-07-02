package com.duitddu.app.mvi.pokedex.ui.detail

import androidx.lifecycle.SavedStateHandle
import com.duitddu.app.mvi.pokedex.data.repository.PokemonRepository
import com.duitddu.app.mvi.pokedex.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    arguments: SavedStateHandle,
    private val pokemonRepository: PokemonRepository
): BaseViewModel<PokemonDetailState, PokemonDetailEvent>() {
    override fun createInitialState(): PokemonDetailState {
        TODO("Not yet implemented")
    }

    override fun onEvent(event: PokemonDetailEvent) {
        TODO("Not yet implemented")
    }

}
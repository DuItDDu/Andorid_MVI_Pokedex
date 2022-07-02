package com.duitddu.app.mvi.pokedex.ui.list

import com.duitddu.app.mvi.pokedex.data.repository.PokemonRepository
import com.duitddu.app.mvi.pokedex.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonRepository
): BaseViewModel<PokemonListState, PokemonListEvent>() {
    override fun createInitialState(): PokemonListState {
        TODO("Not yet implemented")
    }

    override fun onEvent(event: PokemonListEvent) {
        TODO("Not yet implemented")
    }

    override val state: StateFlow<PokemonListState>
        get() = TODO("Not yet implemented")
}
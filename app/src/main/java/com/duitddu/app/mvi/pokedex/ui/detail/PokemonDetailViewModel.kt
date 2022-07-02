package com.duitddu.app.mvi.pokedex.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.duitddu.app.mvi.pokedex.data.repository.PokemonRepository
import com.duitddu.app.mvi.pokedex.mvi.BaseViewModel
import com.duitddu.app.mvi.pokedex.mvi.EmptySideEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    arguments: SavedStateHandle,
    private val pokemonRepository: PokemonRepository
): BaseViewModel<PokemonDetailState, PokemonDetailEvent, EmptySideEffect>() {

    init {
        arguments.get<String>("name")?.let {
            loadPokemon(it)
        } ?: run {
            setState { PokemonDetailState.Error(IllegalStateException()) }
        }
    }

    private fun loadPokemon(name: String) {
        viewModelScope.launch {
            runCatching {
                pokemonRepository.getPokemonDetail(name)
            }.onSuccess {
                setState {
                    PokemonDetailState.Loaded(it)
                }
            }.onFailure {
                setState {
                    PokemonDetailState.Error(it)
                }
            }
        }
    }

    override fun createInitialState(): PokemonDetailState =
        PokemonDetailState.Loading

    override fun onEvent(event: PokemonDetailEvent) {

    }

}
package com.duitddu.app.mvi.pokedex.ui.list

import androidx.lifecycle.viewModelScope
import com.duitddu.app.mvi.pokedex.data.repository.PokemonRepository
import com.duitddu.app.mvi.pokedex.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonRepository
): BaseViewModel<PokemonListState, PokemonListEvent>() {
    init {
        viewModelScope.launch {
            runCatching {
                repository.getPokemonPagingSource(POKEMON_LIST_LIMIT)
            }.onSuccess {
                setState {
                    PokemonListState.Loaded(it)
                }
            }.onFailure {
                setState {
                    PokemonListState.Error(it)
                }
            }
        }
    }

    override fun createInitialState(): PokemonListState =
        PokemonListState.Loading

    override fun onEvent(event: PokemonListEvent) {
        when (event) {
            is PokemonListEvent.SelectPokemon -> {

            }
        }
    }

    companion object {
        private const val POKEMON_LIST_LIMIT = 50
    }
}
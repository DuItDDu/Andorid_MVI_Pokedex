package com.duitddu.app.mvi.pokedex.mvi

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<S: UiState, E: UiEvent> : ViewModel() {
    abstract fun createInitialState(): S
    abstract val state: StateFlow<S>
    abstract fun onEvent(event: E)

    private val initialState: S by lazy { createInitialState() }

    private val _uiState: MutableState<S> = mutableStateOf(initialState)
    val uiState: State<S> = _uiState

    private val _event: MutableSharedFlow<E> = MutableSharedFlow()

    init {
        collectEvents()
    }

    fun emitEvent(event: E) {
        viewModelScope.launch { _event.tryEmit(event) }
    }

    protected fun setState(reducer: S.() -> S) {
        val newState = uiState.value.reducer()
        _uiState.value = newState
    }

    private fun collectEvents() {
        viewModelScope.launch {
            _event.collect { onEvent(it) }
        }
    }
}


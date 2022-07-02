package com.duitddu.app.mvi.pokedex.ui.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ErrorTextView(throwable: Throwable) {
    Text(text = throwable.toString())
}
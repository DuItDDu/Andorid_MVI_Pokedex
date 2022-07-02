package com.duitddu.app.mvi.pokedex.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.navigation.compose.rememberNavController
import com.duitddu.app.mvi.pokedex.ui.theme.MviPokedexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MviPokedexTheme {
                Scaffold {
                    val navController = rememberNavController()
                    MainScreen(navController)
                }
            }
        }
    }
}
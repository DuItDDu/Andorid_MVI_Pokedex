package com.duitddu.app.mvi.pokedex.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.duitddu.app.mvi.pokedex.ui.theme.MviPokedexTheme
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MviPokedexTheme {
                rememberSystemUiController().apply { ConfigSystemUi(this) }

                Scaffold {
                    val navController = rememberNavController()
                    MainScreen(navController)
                }
            }
        }
    }

    @Composable
    private fun ConfigSystemUi(controller: SystemUiController) {
        controller.apply {
            setSystemBarsColor(
                color = MaterialTheme.colors.primary
            )
        }
    }
}
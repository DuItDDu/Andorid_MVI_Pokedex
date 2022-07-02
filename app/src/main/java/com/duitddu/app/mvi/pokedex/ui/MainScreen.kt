package com.duitddu.app.mvi.pokedex.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.duitddu.app.mvi.pokedex.ui.detail.PokemonDetailScreen
import com.duitddu.app.mvi.pokedex.ui.detail.PokemonDetailViewModel
import com.duitddu.app.mvi.pokedex.ui.list.PokemonListScreen
import com.duitddu.app.mvi.pokedex.ui.list.PokemonListSideEffect
import com.duitddu.app.mvi.pokedex.ui.list.PokemonListViewModel

@Composable
fun MainScreen(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Navigation.List.route
    ) {
        composable(Navigation.List.route) {
            val viewModel: PokemonListViewModel = hiltViewModel()
            PokemonListScreen(viewModel) {
                when (it) {
                    is PokemonListSideEffect.NavigateDetail -> {
                        navController.navigate(
                            Navigation.Detail.routeWithParam(it.pokemon)
                        )
                    }
                }
            }
        }

        composable(
            route = Navigation.Detail.route,
            arguments = Navigation.Detail.arguments
        ) {
            val viewModel: PokemonDetailViewModel = hiltViewModel()
            PokemonDetailScreen(viewModel)
        }
    }
}
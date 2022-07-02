package com.duitddu.app.mvi.pokedex.ui

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.duitddu.app.mvi.pokedex.model.PokemonModel

sealed class Navigation(
    val route: String
) {
    object List : Navigation("list")

    object Detail : Navigation("detail/{name}") {
        fun routeWithParam(pokemon: PokemonModel): String =
            "detail/${pokemon.name}"

        val arguments = listOf<NamedNavArgument>(
            navArgument("name") { type = NavType.StringType }
        )
    }
}
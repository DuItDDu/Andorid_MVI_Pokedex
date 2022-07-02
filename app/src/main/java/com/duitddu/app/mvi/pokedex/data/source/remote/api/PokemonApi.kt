package com.duitddu.app.mvi.pokedex.data.source.remote.api

import com.duitddu.app.mvi.pokedex.data.source.remote.api.response.PokemonDetailResponse
import com.duitddu.app.mvi.pokedex.data.source.remote.api.response.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonListResponse

    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(
        @Path("name") name: String
    ): PokemonDetailResponse
}
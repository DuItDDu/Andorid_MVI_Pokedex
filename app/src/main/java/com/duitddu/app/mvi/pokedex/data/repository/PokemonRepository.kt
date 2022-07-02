package com.duitddu.app.mvi.pokedex.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.duitddu.app.mvi.pokedex.data.mapper.PokemonMapper
import com.duitddu.app.mvi.pokedex.data.source.remote.api.PokemonApi
import com.duitddu.app.mvi.pokedex.data.source.remote.paging.PokemonPagingSource
import com.duitddu.app.mvi.pokedex.model.PokemonDetailModel
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val pokemonApi: PokemonApi,
    private val pokemonMapper: PokemonMapper
) {
    fun getPokemonPagingSource(
        limit: Int
    ) = Pager(
        config = PagingConfig(50)
    ) {
        PokemonPagingSource(pokemonApi, pokemonMapper, limit)
    }.flow

    suspend fun getPokemonDetail(name: String) : PokemonDetailModel =
        pokemonApi.getPokemonDetail(name).let { pokemonMapper.mapTo(it) }
}
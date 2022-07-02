package com.duitddu.app.mvi.pokedex.data.source.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.duitddu.app.mvi.pokedex.data.mapper.PokemonMapper
import com.duitddu.app.mvi.pokedex.data.source.remote.api.PokemonApi
import com.duitddu.app.mvi.pokedex.model.PokemonModel

class PokemonPagingSource(
    private val pokemonApi: PokemonApi,
    private val pokemonMapper: PokemonMapper,
    private val limit: Int
) : PagingSource<Int, PokemonModel>() {
    override fun getRefreshKey(state: PagingState<Int, PokemonModel>): Int? =
        state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonModel> =
        try {
            val nextPage = params.key ?: 0
            val data = pokemonApi.getPokemonList(limit, nextPage).let {
                pokemonMapper.mapTo(it)
            }

            LoadResult.Page(
                data = data,
                prevKey = if (nextPage == 0) null else nextPage - limit,
                nextKey = if (data.isEmpty()) null else nextPage + limit
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
}
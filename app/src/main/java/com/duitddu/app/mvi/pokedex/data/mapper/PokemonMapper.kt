package com.duitddu.app.mvi.pokedex.data.mapper

import com.duitddu.app.mvi.pokedex.data.source.remote.api.response.PokemonDetailResponse
import com.duitddu.app.mvi.pokedex.data.source.remote.api.response.PokemonListItemResponse
import com.duitddu.app.mvi.pokedex.data.source.remote.api.response.PokemonListResponse
import com.duitddu.app.mvi.pokedex.data.source.remote.api.response.PokemonTypeResponse
import com.duitddu.app.mvi.pokedex.model.PokemonDetailModel
import com.duitddu.app.mvi.pokedex.model.PokemonModel
import com.duitddu.app.mvi.pokedex.model.PokemonStatModel
import com.duitddu.app.mvi.pokedex.model.PokemonType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonMapper {
    suspend fun mapTo(response: PokemonListResponse) : List<PokemonModel> =
        withContext(Dispatchers.IO) {
            response.results.map { mapTo(it) }
        }

    private suspend fun mapTo(response: PokemonListItemResponse) : PokemonModel =
        withContext(Dispatchers.IO) {
            PokemonModel(
                response.id,
                response.name,
                response.id.toPokemonImageUrl()
            )
        }

    suspend fun mapTo(response: PokemonDetailResponse) : PokemonDetailModel =
        withContext(Dispatchers.IO) {
            response.run {
                PokemonDetailModel(
                    id,
                    name,
                    id.toPokemonImageUrl(),
                    weight,
                    height,
                    stats.map { PokemonStatModel(it.stat.statName, it.baseStat) },
                    types.map { it.findEntity() }
                )
            }
        }

    private fun Int.toPokemonImageUrl(): String =
        POKEMON_IMAGE_BASE_URL + String.format("%03d.png", this)

    private fun PokemonTypeResponse.findEntity(): PokemonType =
        PokemonType.values().find { it.type == type.name } ?: PokemonType.NORMAL

    companion object {
        private const val POKEMON_IMAGE_BASE_URL = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/"
    }
}
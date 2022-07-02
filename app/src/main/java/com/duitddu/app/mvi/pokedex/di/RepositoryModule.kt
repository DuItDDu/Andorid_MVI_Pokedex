package com.duitddu.app.mvi.pokedex.di

import com.duitddu.app.mvi.pokedex.data.mapper.PokemonMapper
import com.duitddu.app.mvi.pokedex.data.repository.PokemonRepository
import com.duitddu.app.mvi.pokedex.data.source.remote.api.PokemonApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun remotePokemonRepository(
        api: PokemonApi,
        mapper: PokemonMapper
    ) : PokemonRepository = PokemonRepository(api, mapper)
}
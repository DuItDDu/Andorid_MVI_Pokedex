package com.duitddu.app.mvi.pokedex.di

import com.duitddu.app.mvi.pokedex.data.mapper.PokemonMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {
    @Provides
    @Singleton
    fun providePokemonMapper(): PokemonMapper = PokemonMapper()
}
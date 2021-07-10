package com.shivamkumarjha.pokedex.di

import com.shivamkumarjha.pokedex.network.ApiPoke
import com.shivamkumarjha.pokedex.persistence.PokemonDao
import com.shivamkumarjha.pokedex.repository.PokeRepository
import com.shivamkumarjha.pokedex.repository.PokeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun getPokeRepository(apiPoke: ApiPoke, pokemonDao: PokemonDao): PokeRepository {
        return PokeRepositoryImpl(apiPoke, pokemonDao)
    }
}
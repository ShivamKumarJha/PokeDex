package com.shivamkumarjha.pokedex.di

import android.content.Context
import androidx.room.Room
import com.shivamkumarjha.pokedex.config.Constants
import com.shivamkumarjha.pokedex.persistence.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun pokemonDatabase(@ApplicationContext context: Context): PokemonDatabase =
        Room.databaseBuilder(context, PokemonDatabase::class.java, Constants.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun pokemonDao(pokemonDatabase: PokemonDatabase) = pokemonDatabase.pokemonDao()
}
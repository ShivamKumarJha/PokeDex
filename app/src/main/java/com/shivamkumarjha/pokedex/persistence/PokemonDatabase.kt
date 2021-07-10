package com.shivamkumarjha.pokedex.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shivamkumarjha.pokedex.model.PokemonData
import com.shivamkumarjha.pokedex.model.PokemonDetails

@Database(
    entities = [
        PokemonDetails::class,
        PokemonData::class,
    ],
    version = 1
)

@TypeConverters(PokemonTypeConverter::class)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}
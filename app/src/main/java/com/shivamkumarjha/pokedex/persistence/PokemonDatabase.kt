package com.shivamkumarjha.pokedex.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shivamkumarjha.pokedex.model.PokemonDetails
import com.shivamkumarjha.pokedex.model.Result

@Database(
    entities = [
        PokemonDetails::class,
        Result::class,
    ],
    version = 1
)

@TypeConverters(PokemonTypeConverter::class)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}
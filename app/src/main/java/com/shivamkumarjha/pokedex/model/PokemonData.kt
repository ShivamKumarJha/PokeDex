package com.shivamkumarjha.pokedex.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokemonData(
    @PrimaryKey val id: Int,
    val name: String,
    val imageUrl: String
)
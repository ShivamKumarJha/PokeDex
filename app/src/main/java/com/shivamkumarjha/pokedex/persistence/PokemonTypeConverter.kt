package com.shivamkumarjha.pokedex.persistence

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shivamkumarjha.pokedex.model.*

class PokemonTypeConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun stringToList(string: String): List<String> {
            val type = object : TypeToken<List<String>>() {}.type
            return Gson().fromJson(string, type)
        }

        @TypeConverter
        @JvmStatic
        fun listToString(string: List<String>): String {
            val type = object : TypeToken<List<String>>() {}.type
            return Gson().toJson(string, type)
        }

        @TypeConverter
        @JvmStatic
        fun stringToResult(result: String): Result {
            val type = object : TypeToken<Result>() {}.type
            return Gson().fromJson(result, type)
        }

        @TypeConverter
        @JvmStatic
        fun resultToJson(result: Result): String {
            val type = object : TypeToken<Result>() {}.type
            return Gson().toJson(result, type)
        }

        @TypeConverter
        @JvmStatic
        fun stringToSprites(sprites: String): Sprites {
            val type = object : TypeToken<Sprites>() {}.type
            return Gson().fromJson(sprites, type)
        }

        @TypeConverter
        @JvmStatic
        fun spritesToJson(sprites: Sprites): String {
            val type = object : TypeToken<Sprites>() {}.type
            return Gson().toJson(sprites, type)
        }

        @TypeConverter
        @JvmStatic
        fun stringToAbilities(abilities: String): List<Abilities> {
            val type = object : TypeToken<List<Abilities>>() {}.type
            return Gson().fromJson(abilities, type)
        }

        @TypeConverter
        @JvmStatic
        fun abilitiesToJson(abilities: List<Abilities>): String {
            val type = object : TypeToken<List<Abilities>>() {}.type
            return Gson().toJson(abilities, type)
        }

        @TypeConverter
        @JvmStatic
        fun stringToResults(result: String): List<Result> {
            val type = object : TypeToken<List<Result>>() {}.type
            return Gson().fromJson(result, type)
        }

        @TypeConverter
        @JvmStatic
        fun resultToJson(result: List<Result>): String {
            val type = object : TypeToken<List<Result>>() {}.type
            return Gson().toJson(result, type)
        }

        @TypeConverter
        @JvmStatic
        fun stringToGameIndices(gameIndices: String): List<GameIndices> {
            val type = object : TypeToken<List<GameIndices>>() {}.type
            return Gson().fromJson(gameIndices, type)
        }

        @TypeConverter
        @JvmStatic
        fun gameIndicesToJson(gameIndices: List<GameIndices>): String {
            val type = object : TypeToken<List<GameIndices>>() {}.type
            return Gson().toJson(gameIndices, type)
        }

        @TypeConverter
        @JvmStatic
        fun stringToMoves(moves: String): List<Moves> {
            val type = object : TypeToken<List<Moves>>() {}.type
            return Gson().fromJson(moves, type)
        }

        @TypeConverter
        @JvmStatic
        fun movesToJson(moves: List<Moves>): String {
            val type = object : TypeToken<List<Moves>>() {}.type
            return Gson().toJson(moves, type)
        }

        @TypeConverter
        @JvmStatic
        fun stringToStats(stats: String): List<Stats> {
            val type = object : TypeToken<List<Stats>>() {}.type
            return Gson().fromJson(stats, type)
        }

        @TypeConverter
        @JvmStatic
        fun statsToJson(stats: List<Stats>): String {
            val type = object : TypeToken<List<Stats>>() {}.type
            return Gson().toJson(stats, type)
        }

        @TypeConverter
        @JvmStatic
        fun stringToTypes(types: String): List<Types> {
            val type = object : TypeToken<List<Types>>() {}.type
            return Gson().fromJson(types, type)
        }

        @TypeConverter
        @JvmStatic
        fun typesToJson(types: List<Types>): String {
            val type = object : TypeToken<List<Types>>() {}.type
            return Gson().toJson(types, type)
        }

        @TypeConverter
        @JvmStatic
        fun stringToHeldDetails(heldDetails: String): List<HeldDetails> {
            val type = object : TypeToken<List<HeldDetails>>() {}.type
            return Gson().fromJson(heldDetails, type)
        }

        @TypeConverter
        @JvmStatic
        fun heldDetailsToJson(heldDetails: List<HeldDetails>): String {
            val type = object : TypeToken<List<HeldDetails>>() {}.type
            return Gson().toJson(heldDetails, type)
        }
    }
}
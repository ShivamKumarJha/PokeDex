package com.shivamkumarjha.pokedex.persistence

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shivamkumarjha.pokedex.model.*

class PokemonTypeConverter {
    companion object {

        private val gson = Gson()

        @TypeConverter
        @JvmStatic
        fun stringToList(string: String): List<String> {
            val type = object : TypeToken<List<String>>() {}.type
            return gson.fromJson(string, type)
        }

        @TypeConverter
        @JvmStatic
        fun listToString(string: List<String>): String {
            val type = object : TypeToken<List<String>>() {}.type
            return gson.toJson(string, type)
        }

        @TypeConverter
        @JvmStatic
        fun stringToResult(result: String): Result {
            val type = object : TypeToken<Result>() {}.type
            return gson.fromJson(result, type)
        }

        @TypeConverter
        @JvmStatic
        fun resultToJson(result: Result): String {
            val type = object : TypeToken<Result>() {}.type
            return gson.toJson(result, type)
        }

        @TypeConverter
        @JvmStatic
        fun stringToSprites(sprites: String): Sprites {
            val type = object : TypeToken<Sprites>() {}.type
            return gson.fromJson(sprites, type)
        }

        @TypeConverter
        @JvmStatic
        fun spritesToJson(sprites: Sprites): String {
            val type = object : TypeToken<Sprites>() {}.type
            return gson.toJson(sprites, type)
        }

        @TypeConverter
        @JvmStatic
        fun stringToAbilities(abilities: String): List<Abilities> {
            val type = object : TypeToken<List<Abilities>>() {}.type
            return gson.fromJson(abilities, type)
        }

        @TypeConverter
        @JvmStatic
        fun abilitiesToJson(abilities: List<Abilities>): String {
            val type = object : TypeToken<List<Abilities>>() {}.type
            return gson.toJson(abilities, type)
        }

        @TypeConverter
        @JvmStatic
        fun stringToResults(result: String): List<Result> {
            val type = object : TypeToken<List<Result>>() {}.type
            return gson.fromJson(result, type)
        }

        @TypeConverter
        @JvmStatic
        fun resultToJson(result: List<Result>): String {
            val type = object : TypeToken<List<Result>>() {}.type
            return gson.toJson(result, type)
        }

        @TypeConverter
        @JvmStatic
        fun stringToGameIndices(gameIndices: String): List<GameIndices> {
            val type = object : TypeToken<List<GameIndices>>() {}.type
            return gson.fromJson(gameIndices, type)
        }

        @TypeConverter
        @JvmStatic
        fun gameIndicesToJson(gameIndices: List<GameIndices>): String {
            val type = object : TypeToken<List<GameIndices>>() {}.type
            return gson.toJson(gameIndices, type)
        }

        @TypeConverter
        @JvmStatic
        fun stringToMoves(moves: String): List<Moves> {
            val type = object : TypeToken<List<Moves>>() {}.type
            return gson.fromJson(moves, type)
        }

        @TypeConverter
        @JvmStatic
        fun movesToJson(moves: List<Moves>): String {
            val type = object : TypeToken<List<Moves>>() {}.type
            return gson.toJson(moves, type)
        }

        @TypeConverter
        @JvmStatic
        fun stringToStats(stats: String): List<Stats> {
            val type = object : TypeToken<List<Stats>>() {}.type
            return gson.fromJson(stats, type)
        }

        @TypeConverter
        @JvmStatic
        fun statsToJson(stats: List<Stats>): String {
            val type = object : TypeToken<List<Stats>>() {}.type
            return gson.toJson(stats, type)
        }

        @TypeConverter
        @JvmStatic
        fun stringToTypes(types: String): List<Types> {
            val type = object : TypeToken<List<Types>>() {}.type
            return gson.fromJson(types, type)
        }

        @TypeConverter
        @JvmStatic
        fun typesToJson(types: List<Types>): String {
            val type = object : TypeToken<List<Types>>() {}.type
            return gson.toJson(types, type)
        }

        @TypeConverter
        @JvmStatic
        fun stringToHeldDetails(heldDetails: String): List<HeldDetails> {
            val type = object : TypeToken<List<HeldDetails>>() {}.type
            return gson.fromJson(heldDetails, type)
        }

        @TypeConverter
        @JvmStatic
        fun heldDetailsToJson(heldDetails: List<HeldDetails>): String {
            val type = object : TypeToken<List<HeldDetails>>() {}.type
            return gson.toJson(heldDetails, type)
        }

        @TypeConverter
        @JvmStatic
        fun stringToPastTypes(pastTypes: String): List<PastTypes> {
            val type = object : TypeToken<List<PastTypes>>() {}.type
            return gson.fromJson(pastTypes, type)
        }

        @TypeConverter
        @JvmStatic
        fun pastTypesToJson(pastTypes: List<PastTypes>): String {
            val type = object : TypeToken<List<PastTypes>>() {}.type
            return gson.toJson(pastTypes, type)
        }
    }
}
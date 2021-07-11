package com.shivamkumarjha.pokedex.utility

import android.util.Log
import com.shivamkumarjha.pokedex.config.Constants
import com.shivamkumarjha.pokedex.model.PokemonData
import com.shivamkumarjha.pokedex.model.PokemonDetails
import com.shivamkumarjha.pokedex.model.Result

object PokemonUtility {

    fun getPokemonData(result: Result): PokemonData {
        return PokemonData(
            getId(result.url),
            result.name,
            getImageUrl(getId(result.url))
        )
    }

    private fun getId(apiPath: String): Int {
        return try {
            if (apiPath.endsWith("/")) {
                apiPath.dropLast(1).takeLastWhile { it.isDigit() }
            } else {
                apiPath.takeLastWhile { it.isDigit() }
            }.toInt()
        } catch (exception: Exception) {
            Log.e(Constants.TAG, exception.message ?: "Error at PokemonUtility: getId(${apiPath})")
            return 0
        }
    }

    private fun getImageUrl(id: Int): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png"
    }

    fun populateImages(pokemonDetails: PokemonDetails): ArrayList<String> {
        val images: ArrayList<String> = arrayListOf()
        images.add(pokemonDetails.sprites.other.officialArtwork.front_default)
        images.add(pokemonDetails.sprites.back_default)
        if (pokemonDetails.sprites.back_female != null)
            images.add(pokemonDetails.sprites.back_female)
        images.add(pokemonDetails.sprites.back_shiny)
        if (pokemonDetails.sprites.back_shiny_female != null)
            images.add(pokemonDetails.sprites.back_shiny_female)
        images.add(pokemonDetails.sprites.front_default)
        if (pokemonDetails.sprites.front_female != null)
            images.add(pokemonDetails.sprites.front_female)
        images.add(pokemonDetails.sprites.front_shiny)
        if (pokemonDetails.sprites.front_shiny_female != null)
            images.add(pokemonDetails.sprites.front_shiny_female)

        return images
    }

    fun styleId(id: Int): String {
        return when {
            id < 10 -> "#00${id}"
            id < 100 -> "#0${id}"
            else -> "#${id}"
        }
    }
}
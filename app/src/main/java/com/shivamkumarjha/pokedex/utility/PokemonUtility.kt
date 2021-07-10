package com.shivamkumarjha.pokedex.utility

import com.shivamkumarjha.pokedex.model.PokemonData
import com.shivamkumarjha.pokedex.model.Result

object PokemonUtility {

    fun getPokemonData(result: Result): PokemonData {
        return PokemonData(
            getId(result.url),
            result.name,
            getImageUrl(result.url)
        )
    }

    private fun getId(apiPath: String): Int {
        return if (apiPath.endsWith("/")) {
            apiPath.dropLast(1).takeLastWhile { it.isDigit() }
        } else {
            apiPath.takeLastWhile { it.isDigit() }
        }.toInt()
    }

    private fun getImageUrl(apiPath: String): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${
            getId(apiPath)
        }.png"
    }
}
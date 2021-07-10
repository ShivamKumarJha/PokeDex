package com.shivamkumarjha.pokedex.utility

object PokemonUtility {

    fun getImageUrl(apiPath: String): String {
        val number = if (apiPath.endsWith("/")) {
            apiPath.dropLast(1).takeLastWhile { it.isDigit() }
        } else {
            apiPath.takeLastWhile { it.isDigit() }
        }
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${number}.png"
    }
}
package com.shivamkumarjha.pokedex.ui.pokemons.adapter

import com.shivamkumarjha.pokedex.model.PokemonData

interface PokemonClickListener {
    fun onCardClick(pokemonData: PokemonData)
}
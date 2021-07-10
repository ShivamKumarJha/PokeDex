package com.shivamkumarjha.pokedex.ui.pokemons.adapter

import com.shivamkumarjha.pokedex.model.Result

interface PokemonClickListener {
    fun onCardClick(result: Result)
}
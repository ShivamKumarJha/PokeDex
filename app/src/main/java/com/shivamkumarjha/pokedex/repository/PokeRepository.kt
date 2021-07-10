package com.shivamkumarjha.pokedex.repository

import com.shivamkumarjha.pokedex.model.PokemonDetails
import com.shivamkumarjha.pokedex.model.PokemonMain
import com.shivamkumarjha.pokedex.network.Resource
import kotlinx.coroutines.flow.Flow

interface PokeRepository {
    suspend fun getPokemons(offset: Int): Flow<Resource<PokemonMain?>>
    suspend fun getPokemonDetails(id: Int): Flow<Resource<PokemonDetails?>>
}
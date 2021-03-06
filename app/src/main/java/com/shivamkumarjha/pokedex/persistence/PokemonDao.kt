package com.shivamkumarjha.pokedex.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shivamkumarjha.pokedex.model.PokemonData
import com.shivamkumarjha.pokedex.model.PokemonDetails

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPokemon(pokemon: PokemonData)

    @Query("SELECT * FROM pokemon ORDER BY id ASC")
    fun getPokemonsIdAsc(): LiveData<List<PokemonData>>

    @Query("SELECT * FROM pokemon ORDER BY id DESC")
    fun getPokemonsIdDesc(): LiveData<List<PokemonData>>

    @Query("SELECT * FROM pokemon ORDER BY name ASC")
    fun getPokemonsNameAsc(): LiveData<List<PokemonData>>

    @Query("SELECT * FROM pokemon ORDER BY name DESC")
    fun getPokemonsNameDesc(): LiveData<List<PokemonData>>

    @Query("DELETE FROM pokemon")
    fun clearPokemons()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPokemonDetail(pokemonDetails: PokemonDetails)

    @Query("SELECT * FROM details where id=:id")
    fun getPokemonDetail(id: Int): LiveData<PokemonDetails?>
}
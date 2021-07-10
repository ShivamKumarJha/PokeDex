package com.shivamkumarjha.pokedex.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shivamkumarjha.pokedex.model.PokemonDetails
import com.shivamkumarjha.pokedex.model.Result

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPokemon(pokemon: Result)

    @Query("SELECT * FROM pokemon ORDER BY url ASC")
    fun getPokemons(): LiveData<List<Result>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPokemonDetail(pokemonDetails: PokemonDetails)

    @Query("SELECT * FROM details where id=:id")
    fun getPokemonDetail(id: Int): LiveData<PokemonDetails?>
}
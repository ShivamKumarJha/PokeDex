package com.shivamkumarjha.pokedex.model

import com.google.gson.annotations.SerializedName

data class PokemonMain(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String,
    @SerializedName("previous") val previous: String,
    @SerializedName("results") val results: ArrayList<Result>
)
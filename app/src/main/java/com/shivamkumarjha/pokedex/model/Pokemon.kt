package com.shivamkumarjha.pokedex.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("is_hidden") val is_hidden: Boolean,
    @SerializedName("pokemon") val pokemon: Result,
    @SerializedName("slot") val slot: Int
)
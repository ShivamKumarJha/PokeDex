package com.shivamkumarjha.pokedex.model

import com.google.gson.annotations.SerializedName

data class GenerationVI(
    @SerializedName("omegaruby-alphasapphire") val omegarubyAlphasapphire: PropertyIV,
    @SerializedName("x-y") val xy: PropertyIV
)
package com.shivamkumarjha.pokedex.model

import com.google.gson.annotations.SerializedName

data class GenerationVI(
    @SerializedName("omegaruby-alphasapphire") val omegarubyAlphasapphire: OmegarubyAlphasapphire,
    @SerializedName("x-y") val xy: Xy
)
package com.shivamkumarjha.pokedex.model

import com.google.gson.annotations.SerializedName

data class GenerationI(
    @SerializedName("red-blue") val redBlue: PropertyV,
    @SerializedName("yellow") val yellow: PropertyV
)
package com.shivamkumarjha.pokedex.model

import com.google.gson.annotations.SerializedName

data class GenerationVII(
    @SerializedName("icons") val icons: PropertyIII,
    @SerializedName("ultra-sun-ultra-moon") val ultraSunUltraMoon: UltraSunUltraMoon
)
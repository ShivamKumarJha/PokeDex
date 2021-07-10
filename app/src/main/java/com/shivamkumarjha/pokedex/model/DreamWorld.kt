package com.shivamkumarjha.pokedex.model

import com.google.gson.annotations.SerializedName

data class DreamWorld(
    @SerializedName("front_default") val front_default: String,
    @SerializedName("front_female") val front_female: String
)
package com.shivamkumarjha.pokedex.model

import com.google.gson.annotations.SerializedName

data class Names(
    @SerializedName("language") val language: Result,
    @SerializedName("name") val name: String
)
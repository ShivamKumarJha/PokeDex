package com.shivamkumarjha.pokedex.model

import com.google.gson.annotations.SerializedName

data class VersionDetails(
    @SerializedName("rarity") val rarity: Int,
    @SerializedName("version") val version: Result
)
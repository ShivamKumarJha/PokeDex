package com.shivamkumarjha.pokedex.model

import com.google.gson.annotations.SerializedName

data class FlavorTextEntries(
    @SerializedName("flavor_text") val flavor_text: String,
    @SerializedName("language") val language: Result,
    @SerializedName("version_group") val version_group: Result
)
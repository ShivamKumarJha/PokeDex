package com.shivamkumarjha.pokedex.model

import com.google.gson.annotations.SerializedName

data class EffectEntries(
    @SerializedName("effect") val effect: String,
    @SerializedName("language") val language: Result,
    @SerializedName("short_effect") val short_effect: String
)
package com.shivamkumarjha.pokedex.model

import com.google.gson.annotations.SerializedName

data class GenerationII(
    @SerializedName("crystal") val crystal: PropertyII,
    @SerializedName("gold") val gold: PropertyII,
    @SerializedName("silver") val silver: PropertyII
)
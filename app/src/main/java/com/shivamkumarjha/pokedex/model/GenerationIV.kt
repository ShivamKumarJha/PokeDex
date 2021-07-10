package com.shivamkumarjha.pokedex.model

import com.google.gson.annotations.SerializedName

data class GenerationIV(
    @SerializedName("diamond-pearl") val diamondPearl: PropertyI,
    @SerializedName("heartgold-soulsilver") val heartgoldSoulsilver: PropertyI,
    @SerializedName("platinum") val platinum: PropertyI
)
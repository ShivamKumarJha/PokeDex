package com.shivamkumarjha.pokedex.model

import com.google.gson.annotations.SerializedName

data class GenerationIV(
    @SerializedName("diamond-pearl") val diamondPearl: Property,
    @SerializedName("heartgold-soulsilver") val heartgoldSoulsilver: Property,
    @SerializedName("platinum") val platinum: Property
)
package com.shivamkumarjha.pokedex.model

import com.google.gson.annotations.SerializedName

data class GenerationIV(
    @SerializedName("diamond-pearl") val diamondPearl: DiamondPearl,
    @SerializedName("heartgold-soulsilver") val heartgoldSoulsilver: HeartGoldSoulSilver,
    @SerializedName("platinum") val platinum: Platinum
)
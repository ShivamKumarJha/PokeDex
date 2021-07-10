package com.shivamkumarjha.pokedex.model

import com.google.gson.annotations.SerializedName

data class Abilities(
    @SerializedName("ability") val ability: Result,
    @SerializedName("is_hidden") val is_hidden: Boolean,
    @SerializedName("slot") val slot: Int
)
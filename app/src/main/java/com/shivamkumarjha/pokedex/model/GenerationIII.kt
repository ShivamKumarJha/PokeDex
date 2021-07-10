package com.shivamkumarjha.pokedex.model

import com.google.gson.annotations.SerializedName

data class GenerationIII(
    @SerializedName("emerald") val emerald: Emerald,
    @SerializedName("firered-leafgreen") val fireredLeafGreen: PropertyII,
    @SerializedName("ruby-sapphire") val rubySapphire: PropertyII
)
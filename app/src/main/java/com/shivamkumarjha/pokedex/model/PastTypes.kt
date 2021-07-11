package com.shivamkumarjha.pokedex.model

import com.google.gson.annotations.SerializedName

data class PastTypes(
    @SerializedName("generation") val generation: Result,
    @SerializedName("types") val types: List<Types>
)
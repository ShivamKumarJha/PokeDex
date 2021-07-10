package com.shivamkumarjha.pokedex.model

import com.google.gson.annotations.SerializedName

data class Moves(
    @SerializedName("move") val move: Result,
    @SerializedName("version_group_details") val version_group_details: List<VersionGroupDetails>
)
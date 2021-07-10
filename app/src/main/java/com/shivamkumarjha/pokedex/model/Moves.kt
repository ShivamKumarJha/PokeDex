package com.shivamkumarjha.pokedex.model

import com.google.gson.annotations.SerializedName

data class Moves(
    @SerializedName("move") val move: Move,
    @SerializedName("version_group_details") val version_group_details: List<VersionGroupDetails>
)
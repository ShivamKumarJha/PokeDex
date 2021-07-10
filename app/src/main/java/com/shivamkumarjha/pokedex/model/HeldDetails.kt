package com.shivamkumarjha.pokedex.model

import com.google.gson.annotations.SerializedName

data class HeldDetails(
    @SerializedName("item") val item: Result,
    @SerializedName("version_details") val version_details: List<VersionDetails>
)
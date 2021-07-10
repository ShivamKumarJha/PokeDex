package com.shivamkumarjha.pokedex.model

import com.google.gson.annotations.SerializedName

data class Other(
    @SerializedName("dream_world") val dream_world: DreamWorld,
    @SerializedName("official-artwork") val officialArtwork: OfficialArtwork
)
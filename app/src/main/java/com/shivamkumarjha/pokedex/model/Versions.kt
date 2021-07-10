package com.shivamkumarjha.pokedex.model

import com.google.gson.annotations.SerializedName

data class Versions(
    @SerializedName("generation-i") val generationI: GenerationI,
    @SerializedName("generation-ii") val generationII: GenerationII,
    @SerializedName("generation-iii") val generationIII: GenerationIII,
    @SerializedName("generation-iv") val generationIV: GenerationIV,
    @SerializedName("generation-v") val generationV: GenerationV,
    @SerializedName("generation-vi") val generationVI: GenerationVI,
    @SerializedName("generation-vii") val generationVII: GenerationVII,
    @SerializedName("generation-viii") val generationVIII: GenerationVIII
)
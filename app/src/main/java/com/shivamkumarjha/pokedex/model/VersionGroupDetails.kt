package com.shivamkumarjha.pokedex.model

import com.google.gson.annotations.SerializedName

data class VersionGroupDetails(
    @SerializedName("level_learned_at") val level_learned_at: Int,
    @SerializedName("move_learn_method") val move_learn_method: MoveLearnMethod,
    @SerializedName("version_group") val version_group: VersionGroup
)
package com.shivamkumarjha.pokedex.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "pokemon")
data class Result(
    @SerializedName("name") val name: String,
    @PrimaryKey @SerializedName("url") val url: String
)
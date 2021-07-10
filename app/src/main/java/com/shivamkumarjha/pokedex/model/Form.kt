package com.shivamkumarjha.pokedex.model

import com.google.gson.annotations.SerializedName

data class Form(
    @SerializedName("form_name") val form_name: String,
    @SerializedName("form_names") val form_names: List<String>,
    @SerializedName("form_order") val form_order: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("is_battle_only") val is_battle_only: Boolean,
    @SerializedName("is_default") val is_default: Boolean,
    @SerializedName("is_mega") val is_mega: Boolean,
    @SerializedName("name") val name: String,
    @SerializedName("names") val names: List<String>,
    @SerializedName("order") val order: Int,
    @SerializedName("pokemon") val pokemon: Result,
    @SerializedName("sprites") val sprites: PropertyI,
    @SerializedName("types") val types: List<Types>,
    @SerializedName("version_group") val version_group: Result
)
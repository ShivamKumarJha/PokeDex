package com.shivamkumarjha.pokedex.model

import com.google.gson.annotations.SerializedName

data class Ability(
    @SerializedName("effect_changes") val effect_changes: ArrayList<String>,
    @SerializedName("effect_entries") val effect_entries: ArrayList<EffectEntries>,
    @SerializedName("flavor_text_entries") val flavor_text_entries: ArrayList<FlavorTextEntries>,
    @SerializedName("generation") val generation: Result,
    @SerializedName("id") val id: Int,
    @SerializedName("is_main_series") val is_main_series: Boolean,
    @SerializedName("name") val name: String,
    @SerializedName("names") val names: ArrayList<Names>,
    @SerializedName("pokemon") val pokemon: ArrayList<Pokemon>
)
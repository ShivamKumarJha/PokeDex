package com.shivamkumarjha.pokedex.persistence

import android.content.Context
import android.content.SharedPreferences
import com.shivamkumarjha.pokedex.config.Constants

class PreferenceManager(context: Context) {

    private val pref: SharedPreferences =
        context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)
}
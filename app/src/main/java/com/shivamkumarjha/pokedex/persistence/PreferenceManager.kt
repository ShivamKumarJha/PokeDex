package com.shivamkumarjha.pokedex.persistence

import android.content.Context
import android.content.SharedPreferences
import com.shivamkumarjha.pokedex.config.Constants

class PreferenceManager(context: Context) {

    private val pref: SharedPreferences =
        context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)

    var filter: Int
        get() = pref.getInt(Constants.PREF_FILTER, 0)
        set(filter) = pref.edit().putInt(Constants.PREF_FILTER, filter).apply()
}
package com.shivamkumarjha.pokedex.persistence

import android.content.Context
import android.content.SharedPreferences
import com.shivamkumarjha.pokedex.config.Constants

class PreferenceManager(context: Context) {

    private val pref: SharedPreferences =
        context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)

    var sort: Int
        get() = pref.getInt(Constants.PREF_SORT, 0)
        set(sort) = pref.edit().putInt(Constants.PREF_SORT, sort).apply()
}
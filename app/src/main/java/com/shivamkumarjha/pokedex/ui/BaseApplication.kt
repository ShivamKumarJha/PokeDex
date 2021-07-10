package com.shivamkumarjha.pokedex.ui

import android.app.Application
import com.facebook.stetho.Stetho
import com.shivamkumarjha.pokedex.BuildConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(applicationContext)
        }
    }
}
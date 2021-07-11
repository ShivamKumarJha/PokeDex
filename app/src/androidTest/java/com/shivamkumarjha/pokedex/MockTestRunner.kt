package com.shivamkumarjha.pokedex

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.shivamkumarjha.pokedex.ui.BaseApplication

class MockTestRunner : AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?, className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, BaseApplication::class.java.name, context)
    }
}
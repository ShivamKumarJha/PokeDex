package com.shivamkumarjha.pokedex

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.shivamkumarjha.pokedex.ui.MainActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Before
    fun appLaunchesSuccessfully() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun onLaunchIsContainerDisplayed() {
        onView(withId(R.id.container)).check(matches(isDisplayed()))
    }
}
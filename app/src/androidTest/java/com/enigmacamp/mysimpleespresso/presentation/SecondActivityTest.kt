package com.enigmacamp.mysimpleespresso.presentation

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.enigmacamp.mysimpleespresso.R
import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SecondActivityTest {
    private lateinit var scenario: ActivityScenario<SecondActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(SecondActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)
    }


    @Test
    fun shouldShowMainActivity_whenButtonBackClick() {
        onView(withId(R.id.buttonHome)).perform(click())
        Truth.assertThat(scenario.state).isEqualTo(Lifecycle.State.DESTROYED)
    }
}
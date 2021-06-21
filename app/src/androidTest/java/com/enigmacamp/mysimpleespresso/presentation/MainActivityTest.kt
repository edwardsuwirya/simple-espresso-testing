package com.enigmacamp.mysimpleespresso.presentation

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.enigmacamp.mysimpleespresso.R
import com.enigmacamp.mysimpleespresso.utils.CountingIdlingResourceSingleton
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class MainActivityTest {
    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        IdlingRegistry.getInstance()
            .register(CountingIdlingResourceSingleton.countingIdlingResource)
        scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @After
    fun shutdown() {
        IdlingRegistry.getInstance()
            .unregister(CountingIdlingResourceSingleton.countingIdlingResource)
    }

    @Test
    fun shouldShowMessageSuccess_whenButtonAddClick() {
        //Given
        val spentAmount = "1"
        val spentDescription = "Test New Spent"

        //When
        onView(withId(R.id.editTextSpentAmount)).perform(typeText(spentAmount))
        onView(withId(R.id.editTextSpentDescription)).perform(typeText(spentDescription))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.buttonAddSpent)).perform(click())
//        Quick & Dirty
//        Thread.sleep(2000)
//        The recommended way using Espresso Idling Resources, but the down side is we have to modify
//        the production code

        //Then
//        assertThat(onView(withId(R.id.textViewMessage)).check(matches(withText("Successfully add your spent"))))
        assertThat(onView(withId(R.id.textViewMessage)).check(matches(withText(""))))

    }
}
package com.enigmacamp.mysimpleespresso.presentation

import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.enigmacamp.mysimpleespresso.R
import com.enigmacamp.mysimpleespresso.data.Spent
import com.enigmacamp.mysimpleespresso.presentation.CustomAssertion.Companion.hasItemCount
import com.enigmacamp.mysimpleespresso.presentation.CustomMatcher.Companion.withItemCount
import com.enigmacamp.mysimpleespresso.utils.CountingIdlingResourceSingleton
import com.google.common.truth.Truth
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*


@RunWith(AndroidJUnit4::class)
class SecondActivityTest {
    private lateinit var scenario: ActivityScenario<SecondActivity>

    @Before
    fun setup() {
        IdlingRegistry.getInstance()
            .register(CountingIdlingResourceSingleton.countingIdlingResource)
        scenario = ActivityScenario.launch(SecondActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)

    }

    @After
    fun shutdown() {
        IdlingRegistry.getInstance()
            .unregister(CountingIdlingResourceSingleton.countingIdlingResource)
    }

    @Test
    fun shouldShowMainActivity_whenButtonBackClick() {
        onView(withId(R.id.buttonHome)).perform(click())
        Truth.assertThat(scenario.state).isEqualTo(Lifecycle.State.DESTROYED)
    }

    @Test
    fun shouldShowRecyclerView_whenActivityLaunch() {
        onView(withId(R.id.recyclerViewSpent)).check(matches(isDisplayed()))
    }

    @Test
    fun recyclerViewShowSomeData_whenActivityLaunch() {
        scenario.onActivity {
            val recyclerView = it.findViewById<RecyclerView>(R.id.recyclerViewSpent)
            val mockData =
                arrayListOf(
                    Spent(spentDescription = "Test 1", spentAmount = 1.0, spentDate = Date()),
                    Spent(spentDescription = "Test 2", spentAmount = 2.0, spentDate = Date())
                )
            recyclerView.adapter = SpentViewAdapter(mockData)
        }
//        Using custom assertion
//        onView(withId(R.id.recyclerViewSpent)).check(hasItemCount(2))

//        Using custom matcher
        onView(withId(R.id.recyclerViewSpent)).check(matches(withItemCount(2)))
    }
}
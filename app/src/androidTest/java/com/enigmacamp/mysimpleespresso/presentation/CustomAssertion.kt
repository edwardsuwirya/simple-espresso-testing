package com.enigmacamp.mysimpleespresso.presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers

class CustomAssertion {
    companion object {
        fun hasItemCount(count: Int): ViewAssertion = RecyclerViewItemCountAssertion(count)
    }

    private class RecyclerViewItemCountAssertion(val count: Int) : ViewAssertion {
        override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
            if (noViewFoundException != null) {
                throw noViewFoundException
            }
            if (view !is RecyclerView) {
                throw IllegalStateException("The asserted view is not RecyclerView")
            }
            if (view.adapter == null) {
                throw IllegalStateException("No adapter is assigned to RecyclerView")
            }
            ViewMatchers.assertThat(
                "RecyclerView item count",
                view.adapter!!.itemCount,
                CoreMatchers.equalTo(count)
            )
        }

    }
}
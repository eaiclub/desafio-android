package com.jess.eaiclubnasa.utils

import android.app.Activity
import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matcher
import org.hamcrest.Matchers.containsString
import java.util.concurrent.TimeUnit

object HomeUtil {

    fun checkContainsTextIsDisplayed(text: String) {
        onView(withText(containsString(text))).check(matches(isDisplayed()))
    }

    fun checkTextIsDisplayed(text: Any) {
        if (text is String) {
            onView(withText(text)).check(matches(isDisplayed()))
        } else if (text is Int) {
            onView(withText(text)).check(matches(isDisplayed()))
        }
    }

    fun checkIdIsDisplayed(id: Int) {
        onView(withId(id)).check(matches(isDisplayed()))
    }

    fun clickById(id: Int) {
        onView(withId(id)).perform(click())
    }

    fun waitForElementId(id: Int, time: Long) {
        onView(isRoot()).perform(waitForId(id, TimeUnit.SECONDS.toMillis(time)))
    }

    fun scroll(recyclerId: Int, pos: Int) {
        onView(withId(recyclerId))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(pos))
    }

    fun clickRecyclerViewItem(@IdRes recyclerId: Int, item: Int) {
        onView(withId(recyclerId)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                item,
                click()
            )
        )
    }

    fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
        return RecyclerViewMatcher(recyclerViewId)
    }

    fun checkTextIsDisplayedOnRecyclerViewPosition(id: Int, position: Int, text: String) {
        onView(withRecyclerView(id).atPosition(position))
            .check(matches(hasDescendant(withText(text))))
    }

    fun scrollToRecyclerViewLastPosition(
        activity: Activity,
        @IdRes recyclerViewId: Int
    ) {
        val recyclerView = activity.findViewById<RecyclerView>(recyclerViewId)
        val itemCount = recyclerView.adapter?.itemCount as Int
        onView(withId(recyclerViewId))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(itemCount - 1))
    }

    fun waitForId(viewId: Int, millis: Long): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return ViewMatchers.isRoot()
            }

            override fun getDescription(): String {
                return "wait for a specific view with id <$viewId> during $millis millis."
            }

            override fun perform(uiController: UiController, view: View) {
                uiController.loopMainThreadForAtLeast(millis)
            }
        }
    }
}

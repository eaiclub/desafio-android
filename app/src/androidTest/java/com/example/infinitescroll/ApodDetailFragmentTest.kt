package com.example.infinitescroll

import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import com.example.infinitescroll.data.model.Apod
import com.example.infinitescroll.presentation.ApodActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class ApodDetailFragmentTest {

    @Rule
    @JvmField
    val activityScenario : ActivityScenarioRule<ApodActivity> = ActivityScenarioRule(ApodActivity::class.java)

    private val apod = Apod(
            "copyright",
            Calendar.getInstance(),
            "explanation",
            "hdUrl",
            "mediaType",
            "serviceVersion",
            "title",
            "url"
    )

    @Before
    fun goToApodDetailFragment() {
        activityScenario.scenario.onActivity { activity ->
            runOnUiThread {
                val bundle = bundleOf("apod" to apod)
                val navHostFragment = activity.supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
                navHostFragment.navController.navigate(R.id.apodDetailFragment, bundle)
            }
        }
    }

    @Test
    fun testFillContent() {
        onView(withId(R.id.title)).check(matches(withText(apod.title)))
        onView(withId(R.id.explanation)).check(matches(withText(apod.explanation)))
        onView(withId(R.id.date)).check(matches(withText(apod.getFormattedDate())))
    }
}
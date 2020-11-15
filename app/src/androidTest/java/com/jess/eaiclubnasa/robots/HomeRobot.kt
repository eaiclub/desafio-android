package com.jess.eaiclubnasa.robots

import android.content.Context
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.jess.eaiclubnasa.ApodAPI
import com.jess.eaiclubnasa.R
import com.jess.eaiclubnasa.utils.HomeUtil
import com.jess.eaiclubnasa.view.HomeActivity
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import java.io.IOException
import java.io.InputStream

class HomeRobot(
    private val mockWebServer: MockWebServer?,
    private val activityTestRule: ActivityTestRule<HomeActivity>
) {

    fun setRequest(): HomeRobot {
        mockWebServer?.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                if (request.path?.contains(ApodAPI.SOURCE) == true) {
                    return MockResponse().setBody(
                        readFileFromAssets(
                            "response.json",
                            InstrumentationRegistry.getInstrumentation().context
                        )
                    )
                }

                return MockResponse().setBody(
                    readFileFromAssets(
                        "error_not_found.json",
                        InstrumentationRegistry.getInstrumentation().context
                    )
                )
            }
        }

        return this
    }

    private fun readFileFromAssets(fileName: String, c: Context): String {
        return try {
            val `is`: InputStream = c.assets.open(fileName)
            val size: Int = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    fun startActivity(): HomeRobot {
        activityTestRule.launchActivity(Intent(Intent.ACTION_MAIN))
        return this
    }

    fun finishActivity() {
        activityTestRule.finishActivity()
    }

    fun checkIsDisplayedRecyclerView() {
        onView(withId(R.id.rv_apod))
            .check(matches(isDisplayed()))
    }

    fun checkDetailIsShow(): HomeRobot{
        HomeUtil.waitForElementId(R.id.rv_apod, 1)
        HomeUtil.clickRecyclerViewItem(R.id.rv_apod, 0)
        HomeUtil.checkContainsTextIsDisplayed("Details of the crazed cracks criss-crossing Europa's")

        return this
    }

    fun checkZoomIsShow(): HomeRobot{
        HomeUtil.clickById(R.id.iv_apod_detail)
        HomeUtil.checkTextIsDisplayed("Picture zoom")
        HomeUtil.checkIdIsDisplayed(R.id.iv_apod_image_zoom)

        return this
    }

    fun checkInfinitScroll(): HomeRobot{
        HomeUtil.waitForElementId(R.id.rv_apod, 1)
        HomeUtil.scrollToRecyclerViewLastPosition(activityTestRule.activity, R.id.rv_apod)
        HomeUtil.scroll(R.id.rv_apod, 8)
        HomeUtil.checkTextIsDisplayedOnRecyclerViewPosition(R.id.rv_apod, 8, "Galileo Explores Europa")

        return this
    }
}
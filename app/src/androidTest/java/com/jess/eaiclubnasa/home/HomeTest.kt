package com.jess.eaiclubnasa.home

import androidx.test.rule.ActivityTestRule
import com.jess.eaiclubnasa.ApodService
import com.jess.eaiclubnasa.robots.HomeRobot
import com.jess.eaiclubnasa.view.HomeActivity
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeTest {
    private lateinit var robot: HomeRobot
    private var mockWebServer: MockWebServer? = null

    init {
        mockWebServer = MockWebServer()
        ApodService.setBaseUrl(mockWebServer?.url("/").toString())
    }

    @get:Rule
    var activityRule = ActivityTestRule(HomeActivity::class.java, false, false)

    @Before
    fun setup() {
        robot = HomeRobot(mockWebServer, activityRule)
    }

    @After
    fun tearDown() {
        mockWebServer?.shutdown();
        robot.finishActivity()
    }

    @Test
    fun testNavigateListHome(){
        robot.setRequest()
            .startActivity()
            .checkIsDisplayedRecyclerView()
    }

    @Test
    fun testScrollList() {
        robot.setRequest()
            .startActivity()
            .checkInfinitScroll()
    }

    @Test
    fun testNavigationToDetail(){
        robot.setRequest()
            .startActivity()
            .checkDetailIsShow()
    }

    @Test
    fun testNavigateToFragmentZoom(){
        robot.setRequest()
            .startActivity()
            .checkDetailIsShow()
            .checkZoomIsShow()
    }
}
package com.jess.eaiclubnasa

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.text.ParseException

@RunWith(JUnit4::class)
class ApodUtilsTest {

    @Test
    fun testGetLastFiveDays() {
        val listStrings = arrayListOf(
            "2020-11-13",
            "2020-11-12",
            "2020-11-11",
            "2020-11-10",
            "2020-11-09",
            "2020-11-08"
        )

        val listResult = ApodUtils.getLastFiveDays("2020-11-14")

        assertEquals(listStrings, listResult)
    }

    @Test
    fun testGetCurrentDate() {
        assertEquals("2020-11-14", ApodUtils.getCurrentDate())
    }

    @Test
    fun testFormatDate() {
        assertEquals("06/10/2020", ApodUtils.formatDate("2020-10-06"))
    }

    @Test(expected = ParseException::class)
    fun testFormatDateFail() {
        ApodUtils.formatDate("06/10/2020")
    }
}
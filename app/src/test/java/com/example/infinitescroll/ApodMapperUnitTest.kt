package com.example.infinitescroll

import com.example.infinitescroll.data.mapper.ApodMapper
import com.example.infinitescroll.data.response.ApodResponse
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat

class ApodMapperUnitTest {

    private val response = ApodResponse(
            "copyright",
            "2020-11-08",
            "explanation",
            "hdUrl",
            "mediaType",
            "serviceVersion",
            "title",
            "url"
    )

    private val emptyResponse = ApodResponse(
            null,
            "2020-11-08",
            null,
            null,
            null,
            null,
            null,
            null
    )

    private val mapper = ApodMapper()

    @Test
    fun map_single() {
        val apod = mapper.map(response)
        val date = SimpleDateFormat("yyyy-MM-dd").format(apod.date.time)

        assertEquals(apod.copyright, response.copyright)
        assertEquals(apod.explanation, response.explanation)
        assertEquals(apod.hdUrl, response.hdUrl)
        assertEquals(apod.mediaType, response.mediaType)
        assertEquals(apod.serviceVersion, response.serviceVersion)
        assertEquals(apod.title, response.title)
        assertEquals(apod.url, response.url)
        assertEquals(date, response.date)

    }

    @Test
    fun map_single_empty() {

        val apod = mapper.map(emptyResponse)

        assertEquals(apod.copyright, "")
        assertEquals(apod.explanation, "")
        assertEquals(apod.hdUrl, "")
        assertEquals(apod.mediaType, "")
        assertEquals(apod.serviceVersion, "")
        assertEquals(apod.title, "")
        assertEquals(apod.url, "")

    }

    @Test
    fun map_multiple() {

        val apod = mapper.map(listOf(response, emptyResponse))

        assertEquals(apod.size, 2)
        assertEquals(apod[0].copyright, response.copyright)
        assertEquals(apod[1].copyright, "")

    }

}
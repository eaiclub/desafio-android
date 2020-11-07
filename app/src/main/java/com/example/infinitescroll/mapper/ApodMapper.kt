package com.example.infinitescroll.mapper

import com.example.infinitescroll.model.Apod
import com.example.infinitescroll.model.ApodResponse
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Convert remote apod data to local apod data.
 */
class ApodMapper @Inject constructor() {

    fun map(response : ApodResponse) : Apod {

        val df = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = df.parse(response.date)

        val calendar = Calendar.getInstance()

        date?.let {

            calendar.time = it
        }

        return Apod(
            copyright = response.copyright,
            date = calendar,
            explanation = response.explanation,
            hdUrl = response.hdUrl,
            mediaType = response.mediaType,
            serviceVersion = response.serviceVersion,
            title = response.title,
            url = response.url
        )
    }

}
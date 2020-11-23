package com.example.desafio_android.util

import com.example.desafio_android.common.domain.models.NasaApod
import com.example.desafio_android.common.domain.models.NasaApodResponse
import java.text.SimpleDateFormat
import java.util.*

fun List<NasaApodResponse>.mapForView(): List<NasaApod>{
    return this.map {
        NasaApod(
                it.copyright ?: "",
                it.date ?: "",
                it.explanation ?: "",
                it.hdurl ?: "",
                it.media_type ?: "",
                it.service_version ?: "",
                it.title ?: "",
                it.url ?: ""
        )
    }
}

fun Calendar.toStringDate(): String{
    return SimpleDateFormat(DATE_YEAR_MONTH_DAY, Locale.US).format(this.time)
}
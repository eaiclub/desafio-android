package com.jess.eaiclubnasa

import com.jess.eaiclubnasa.Constants.DATE_PATTERN_DAY_MONTH_YEAR
import com.jess.eaiclubnasa.Constants.DATE_PATTERN_YEAR_MONTH_DAY
import java.text.SimpleDateFormat
import java.util.*

object ApodUtils {
    fun getLastFiveDays(date: String): List<String> {
        val listDate = arrayListOf<String>()
        val dateFormat = SimpleDateFormat(DATE_PATTERN_YEAR_MONTH_DAY, Locale.getDefault())
        val currentCalendar = Calendar.getInstance()
        val newDate = dateFormat.parse(date)
        newDate?.let {
            currentCalendar.time = it

            for (i in 0..5) {
                currentCalendar.add(Calendar.DATE, -1)
                val toDate = dateFormat.format(currentCalendar.time)
                listDate.add(toDate)
            }
        }
        return listDate
    }

    fun getCurrentDate(): String =
        SimpleDateFormat(DATE_PATTERN_YEAR_MONTH_DAY, Locale.getDefault()).format(Date())

    fun formatDate(date: String): String {
        val format = SimpleDateFormat(DATE_PATTERN_YEAR_MONTH_DAY, Locale.getDefault())
        val newDate = format.parse(date)
        val dateToFormat = SimpleDateFormat(DATE_PATTERN_DAY_MONTH_YEAR, Locale.getDefault())
        var dateFormated = ""

        newDate?.let {
            dateFormated = dateToFormat.format(it)
        }
        return dateFormated
    }
}
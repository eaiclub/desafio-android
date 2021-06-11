package com.example.nasapicturesapp.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    private const val dateFormatPattern = "yyyy-MM-dd"
    private const val userDateFormatPattern = "MM/dd/yyyy"

    fun getToday(): String {
        val calendar = Calendar.getInstance()
        val today = calendar.time
        return today.convertToString()
    }

    private fun Date.convertToString(pattern: String): String {
        val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        return dateFormat.format(this)
    }

    private fun Date.convertToString(): String = this.convertToString(dateFormatPattern)

    private fun String.convertToDate() : Date? {
        val dateFormat = SimpleDateFormat(dateFormatPattern, Locale.getDefault())
        return dateFormat.parse(this)
    }

    fun getNextDay(date: String, numberOfDays: Int): String = getDaysFrom(date, numberOfDays)

    fun getPreviewDay(date: String, numberOfDays: Int): String = getDaysFrom(date, -numberOfDays)

    private fun getDaysFrom(date: String, numberOfDays: Int): String {
        val calendar = Calendar.getInstance()
        val mDate = date.convertToDate()
        calendar.time = mDate!!
        calendar.add(Calendar.DAY_OF_YEAR, numberOfDays)
        return calendar.time.convertToString()
    }

    fun convertDateToUserDate(date: String): String? {
        val mDate = date.convertToDate()
        return mDate?.convertToString(userDateFormatPattern)
    }
}
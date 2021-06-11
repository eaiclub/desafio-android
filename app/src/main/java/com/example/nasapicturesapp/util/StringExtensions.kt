package com.example.nasapicturesapp.util

fun String.nextDays(numberOfDays: Int) = DateUtil.getNextDay(this, numberOfDays)

fun String.previewDays(numberOfDays: Int) = DateUtil.getPreviewDay(this, numberOfDays)

fun String.toUserFriendlyDate() = DateUtil.convertDateToUserDate(this).orEmpty()

fun String.hasNextDay() = (this != DateUtil.getToday())
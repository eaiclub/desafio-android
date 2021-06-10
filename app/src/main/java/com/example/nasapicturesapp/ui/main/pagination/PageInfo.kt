package com.example.nasapicturesapp.ui.main.pagination

import com.example.nasapicturesapp.util.lessTenDays
import com.example.nasapicturesapp.util.moreTenDays

class PageInfo(
    var startDate: String,
    var endDate: String
) {
    fun plusOne() = PageInfo(this.startDate.moreTenDays(), this.endDate.moreTenDays())

    fun minusOne() = PageInfo(this.startDate.lessTenDays(), this.endDate.lessTenDays())
}
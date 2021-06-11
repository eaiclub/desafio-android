package com.example.nasapicturesapp.ui.main.pagination

import com.example.nasapicturesapp.util.nextDays
import com.example.nasapicturesapp.util.previewDays

class PageInfo(
    var startDate: String,
    var endDate: String
) {
    fun plusOne() = PageInfo(this.startDate.previewDays(perPage), this.endDate.previewDays(perPage))

    fun minusOne() = PageInfo(this.startDate.nextDays(perPage), this.endDate.nextDays(perPage))

    companion object {
        const val perPage = 10
    }
}
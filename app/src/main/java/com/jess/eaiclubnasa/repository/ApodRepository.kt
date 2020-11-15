package com.jess.eaiclubnasa.repository

import com.jess.eaiclubnasa.ApodService
import com.jess.eaiclubnasa.Constants.API_KEY

class ApodRepository {

    suspend fun getApod(date: String) = ApodService.getApi().getApodDay(API_KEY, date)
}
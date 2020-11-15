package com.jess.eaiclubnasa

import com.jess.eaiclubnasa.model.ApodResult
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodAPI {

    companion object {
        const val SOURCE = "planetary/apod"
    }

    @GET(SOURCE)
    suspend fun getApodDay(
        @Query("api_key") api: String,
        @Query("date") date: String
    ): ApodResult
}
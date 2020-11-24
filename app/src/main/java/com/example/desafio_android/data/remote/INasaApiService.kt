package com.example.desafio_android.data.remote

import com.example.desafio_android.common.domain.models.NasaApodResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface INasaApiService {

    @GET("apod")
    suspend fun getApodsList(
        @Query("api_key") apiKey: String,
        @Query("start_date") startDate : String,
        @Query("end_date") endDate : String
    ) : List<NasaApodResponse>
}
package com.example.iterativechallenger.data.api

import com.example.iterativechallenger.BuildConfig
import com.example.iterativechallenger.data.models.ApodDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
    //    ?api_key=${BuildConfig.API_KEY}&start_date=$startDate&end_date=$endDate
    @GET("apod")
    suspend fun getApods(
            @Query("api_key") apiKey: String,
            @Query("start_date") startDate : String,
            @Query("end_date") endDate : String
    ) : Response<List<ApodDataResponse>>
}
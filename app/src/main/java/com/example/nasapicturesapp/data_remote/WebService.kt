package com.example.nasapicturesapp.data_remote

import com.example.nasapicturesapp.domain.model.Picture
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("apod")
    suspend fun getApod(
        @Query("api_key") apiKey: String,
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String
    ) : List<Picture>
}
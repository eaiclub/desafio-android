package com.example.nasapicturesapp.data_remote

import com.example.nasapicturesapp.data_remote.response.PictureResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET(Constants.Api.apod)
    suspend fun getApod(
        @Query(Constants.Query.apiKey) apiKey: String,
        @Query(Constants.Query.startDate) startDate: String,
        @Query(Constants.Query.endDate) endDate: String
    ) : List<PictureResponse>

    @GET(Constants.Api.apod)
    suspend fun getPicture(
        @Query(Constants.Query.apiKey) apiKey: String,
        @Query(Constants.Query.date) date: String
    ) : PictureResponse
}


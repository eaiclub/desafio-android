package com.example.iterativechallenger.data.api

import com.example.iterativechallenger.data.models.ApodDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET
    suspend fun getApods(@Url url : String) : Response<List<ApodDataResponse>>
}
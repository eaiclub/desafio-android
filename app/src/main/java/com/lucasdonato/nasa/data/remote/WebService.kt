package com.lucasdonato.nasa.data.remote

import com.lucasdonato.nasa.data.model.Apod
import com.lucasdonato.nasa.mechanism.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("planetary/apod")
    fun getList(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") api: String = API_KEY
    ): Call<List<Apod>>

}
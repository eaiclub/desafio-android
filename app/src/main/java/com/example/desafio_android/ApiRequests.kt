package com.example.desafio_android

import com.example.desafio_android.api.PlanetaryJson
import retrofit2.Call
import retrofit2.http.GET

interface ApiRequests {

    @GET("planetary/apod?api_key=DEMO_KEY")
    fun getPlanetary(): Call<PlanetaryJson>
}
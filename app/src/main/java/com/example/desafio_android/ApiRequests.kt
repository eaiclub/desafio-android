package com.example.desafio_android

import com.example.desafio_android.api.PlanetaryJson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiRequests {

//    @GET("planetary/apod?api_key=DEMO_KEY")
//    fun getPlanetary(): Call<PlanetaryJson>


    @GET("/planetary/apod")
    fun getPlanetary(@Query("api_key") api_keyValue: String,
                     @Query("date") dateValue: String
    ): Call<PlanetaryJson>

}

//    @GET("myObjects/{id}")
//    fun myObjectById(@Path("id") id: Int?, @Query("a_param") aParam: String?): Call<MyObject>

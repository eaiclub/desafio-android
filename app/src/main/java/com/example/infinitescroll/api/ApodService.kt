package com.example.infinitescroll.api

import com.example.infinitescroll.BuildConfig
import com.example.infinitescroll.model.ApodResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodService {

    @GET("planetary/apod")
    suspend fun getApod(
        @Query("date") date: String,
        @Query("api_key") apiKey : String = BuildConfig.API_KEY
    ): ApodResponse

    companion object {
        private const val BASE_URL = BuildConfig.BASE_URL

        fun create(): ApodService {
            val logger = HttpLoggingInterceptor().apply { level = Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApodService::class.java)
        }
    }
}

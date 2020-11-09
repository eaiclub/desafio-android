package com.example.infinitescroll.data.api

import com.example.infinitescroll.BuildConfig
import com.example.infinitescroll.data.response.ApodResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface to access apod api.
 */
interface ApodService {

    @GET("planetary/apod")
    suspend fun getApod(
        @Query("date") date: String,
        @Query("api_key") apiKey : String = BuildConfig.API_KEY
    ): ApodResponse

    @GET("planetary/apod")
    suspend fun getApodRange(
            @Query("start_date") startDate: String,
            @Query("end_date") endDate: String,
            @Query("api_key") apiKey : String = BuildConfig.API_KEY
    ): List<ApodResponse>

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

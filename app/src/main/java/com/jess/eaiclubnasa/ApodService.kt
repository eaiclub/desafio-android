package com.jess.eaiclubnasa

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApodService {
    companion object {
        var BASE_URL = "https://api.nasa.gov/"
        private val gson = GsonBuilder().setLenient().create()
        private val httpClient = OkHttpClient.Builder()

        init {
            val httpClient = OkHttpClient.Builder()
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                httpClient.addInterceptor(logging)
            }
        }

        fun getApi(): ApodAPI {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()
                .create(ApodAPI::class.java)
        }

        fun setBaseUrl(baseUrl: String) {
            BASE_URL = baseUrl
        }
    }
}
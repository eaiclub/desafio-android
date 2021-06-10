package com.example.nasapicturesapp.data_remote

import com.example.nasapicturesapp.data_remote.Constants.Api.baseApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object DataRemoteModule {

    @Provides
    fun provideApiService(
        client: OkHttpClient,
        gson: Gson
    ): WebService {
        return Retrofit.Builder().baseUrl(baseApi)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(WebService::class.java)
    }

    @Provides
    fun gson(): Gson = GsonBuilder().create()

    @Provides
    fun client(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
}
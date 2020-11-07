package com.example.iterativechallenger.data.data_sources

import com.example.iterativechallenger.BuildConfig
import com.example.iterativechallenger.core.exceptions.*
import com.example.iterativechallenger.data.api.ApiService
import com.example.iterativechallenger.data.models.ApodDataResponse
import java.text.SimpleDateFormat
import java.util.*

class ApodRemoteDataSourceImpl(private val api : ApiService) : ApodRemoteDataSource {

    override suspend fun getApod(dataInicial : Calendar, dataFinal : Calendar): List<ApodDataResponse> {

        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val startDate = sdf.format(dataInicial.time)
        val endDate = sdf.format(dataFinal.time)

        val response = api.getApods("apod?api_key=${BuildConfig.API_KEY}&start_date$startDate}&end_date$endDate")

        when {
            response.isSuccessful -> {
                return response.body()!!
            }
            response.code() == 401 -> {
                throw InvalidApiKeyThrowable()
            }
            response.code() == 404 -> {
                throw ResourceNotFoundThrowable()
            }
            else -> throw Throwable()
        }
    }
}
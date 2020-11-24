package com.example.iterativechallenger.data.data_sources

import com.example.iterativechallenger.BuildConfig
import com.example.iterativechallenger.core.exceptions.*
import com.example.iterativechallenger.core.utils.Constantes
import com.example.iterativechallenger.data.api.ApiService
import com.example.iterativechallenger.data.models.ApodDataResponse
import java.text.SimpleDateFormat
import java.util.*

class ApodRemoteDataSourceImpl(private val api : ApiService) : ApodRemoteDataSource {

    override suspend fun getApod(dataInicial : String, dataFinal : String): List<ApodDataResponse> {

        val response = api.getApods(BuildConfig.API_KEY, dataInicial, dataFinal)

        when {
            response.isSuccessful -> {
                return response.body()!!
            }
            response.code() == 400 -> {
                throw BadRequestThrowable()
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
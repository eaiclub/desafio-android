package com.example.iterativechallenger.data.data_sources

import com.example.iterativechallenger.data.models.ApodDataResponse
import java.util.*

interface ApodRemoteDataSource {

    suspend fun getApod(dataInicial : String, dataFinal : String): List<ApodDataResponse>
}
package com.example.iterativechallenger.data.repositories

import com.example.iterativechallenger.core.utils.Constantes
import com.example.iterativechallenger.data.data_sources.ApodRemoteDataSource
import com.example.iterativechallenger.data.mappers.ApodDataResponseToApodMapper
import com.example.iterativechallenger.domain.entities.Apod
import com.example.iterativechallenger.domain.repositories.ApodRepository
import java.text.SimpleDateFormat
import java.util.*

class ApodRepositoryImpl(
    private val remoteDataSource: ApodRemoteDataSource,
    private val apodDataResponseToApodMapper: ApodDataResponseToApodMapper
) : ApodRepository{

    override suspend fun getApod(dataInicial: Calendar, dataFinal: Calendar): List<Apod> {

        val sdf = SimpleDateFormat(Constantes.FORMATO_ANO_MES_DIA, Locale.US)
        val startDate = sdf.format(dataInicial.time)
        val endDate = sdf.format(dataFinal.time)

        val response = remoteDataSource.getApod(startDate, endDate)

        return apodDataResponseToApodMapper.map(response)
    }
}
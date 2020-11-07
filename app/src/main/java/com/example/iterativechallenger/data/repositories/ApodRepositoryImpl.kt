package com.example.iterativechallenger.data.repositories

import com.example.iterativechallenger.data.data_sources.ApodRemoteDataSource
import com.example.iterativechallenger.data.mappers.ApodDataResponseToApodMapper
import com.example.iterativechallenger.domain.entities.Apod
import com.example.iterativechallenger.domain.repositories.ApodRepository
import java.util.*

class ApodRepositoryImpl(
    private val remoteDataSource: ApodRemoteDataSource,
    private val apodDataResponseToApodMapper: ApodDataResponseToApodMapper
) : ApodRepository{

    override suspend fun getApod(dataInicial: Calendar, dataFinal: Calendar): List<Apod> {

        val response = remoteDataSource.getApod(dataInicial, dataFinal)

        return apodDataResponseToApodMapper.map(response)
    }
}
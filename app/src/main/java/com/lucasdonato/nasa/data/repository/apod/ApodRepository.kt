package com.lucasdonato.nasa.data.repository.apod

import com.lucasdonato.nasa.data.model.Apod
import com.lucasdonato.nasa.data.remote.dataSource.ApodDataSource
import com.lucasdonato.nasa.data.repository.performRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ApodRepository(private val apodDataSource: ApodDataSource) {

    suspend fun getApodDate(startDate: String, endDate: String) = withContext(Dispatchers.IO) {
        (performRequest(
            apodDataSource.getApod(startDate, endDate).execute(), true) as List<Apod>?)
    }

}
package com.lucasdonato.nasa.data.useCase

import android.content.Context
import com.lucasdonato.nasa.data.model.Apod
import com.lucasdonato.nasa.data.repository.apod.ApodRepository
import com.lucasdonato.nasa.domain.base.runSuspend
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ApodUseCase(private val apodRepository: ApodRepository) {

    suspend fun getData(startDate: String, endDate: String) = withContext(Dispatchers.IO) {
        runSuspend {
            apodRepository.getApodDate(startDate, endDate)
        }
    }

}
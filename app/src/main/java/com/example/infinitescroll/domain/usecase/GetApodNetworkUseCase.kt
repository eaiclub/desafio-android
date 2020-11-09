package com.example.infinitescroll.domain.usecase

import com.example.infinitescroll.data.model.Apod
import com.example.infinitescroll.domain.repository.ApodRepository
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * fetch apods from network.
 */
class GetApodNetworkUseCase @Inject constructor(private val apodRepository: ApodRepository) {

    suspend fun execute(lastApodDate : Calendar?) : List<Apod>{
        val endDate = lastApodDate?.also { it.add(Calendar.DATE, -1) } ?: Calendar.getInstance()

        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val endDateStr = sdf.format(endDate.time)
        val startDateStr = sdf.format(endDate.also { it.add(Calendar.DATE, -30) }.time)

        return apodRepository.loadApodRange(startDateStr, endDateStr).reversed()
    }

}
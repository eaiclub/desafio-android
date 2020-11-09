package com.example.infinitescroll.domain.usecase

import com.example.infinitescroll.data.api.Status
import com.example.infinitescroll.data.model.Apod
import com.example.infinitescroll.domain.repository.ApodRepository
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.Throws

/**
 * fetch apods from network.
 */
class GetApodNetworkUseCase @Inject constructor(private val apodRepository: ApodRepository) {

    @Throws(Exception::class)
    suspend fun execute(lastApodDate : Calendar?) : List<Apod> {
        val endDate = lastApodDate?.also { it.add(Calendar.DATE, -1) } ?: Calendar.getInstance()

        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val endDateStr = sdf.format(endDate.time)
        val startDateStr = sdf.format(endDate.also { it.add(Calendar.DATE, -30) }.time)

        val response = apodRepository.loadApodRange(startDateStr, endDateStr)

        if(response.status == Status.SUCCESS)
            return response.data!!.reversed()
        else
            throw Exception(response.message)
    }

}
package com.example.desafio_android.common.domain.usecase

import com.example.desafio_android.common.domain.models.NasaApod
import com.example.desafio_android.common.domain.repository.NasaRepository
import com.example.desafio_android.util.Resource
import com.example.desafio_android.util.mapForView
import com.example.desafio_android.util.toStringDate
import java.util.*

class ApodUseCase(private val nasaRepo: NasaRepository) {

    suspend fun getApodList(initialDate: Calendar, finalDate: Calendar): Resource<List<NasaApod>>{
        val response = nasaRepo.getApodList(initialDate.toStringDate(), finalDate.toStringDate())
        return Resource(response.status, response.data?.mapForView(), response.message)
    }

}
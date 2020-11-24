package com.example.desafio_android.common.domain.usecase

import com.example.desafio_android.common.domain.models.NasaApod
import com.example.desafio_android.common.domain.repository.NasaRepository
import com.example.desafio_android.util.*
import java.util.*

class ApodUseCase(private val nasaRepo: NasaRepository) {

    suspend fun getApodList(offset: Int): Resource<List<NasaApod>>{
        //The diff between the offsets is the number of items we want on the response
        val initialOffset = - offset - ITENS_ON_RESPONSE
        val initialDate = getDate(initialOffset)
        val finalDate   = getDate(- offset)
        val response = nasaRepo.getApodList(initialDate.toStringDate(), finalDate.toStringDate())
        return Resource(response.status, response.data?.mapForView(), response.message)
    }

    private fun getDate(offset: Int): Calendar{
        val initialDate = Calendar.getInstance()
        initialDate.add(Calendar.DATE, offset)
        return initialDate
    }

}
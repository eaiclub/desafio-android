package com.example.iterativechallenger.domain.usecases

import com.example.iterativechallenger.domain.entities.Apod
import com.example.iterativechallenger.domain.repositories.ApodRepository
import java.util.*

class GetListApod(private val repository: ApodRepository) {

    suspend operator fun invoke(dataInicial : Calendar, dataFinal : Calendar) : List<Apod> = repository.getApod(dataInicial, dataFinal)
}
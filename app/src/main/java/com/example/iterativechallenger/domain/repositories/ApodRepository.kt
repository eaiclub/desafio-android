package com.example.iterativechallenger.domain.repositories

import com.example.iterativechallenger.domain.entities.Apod
import java.util.*

interface ApodRepository {

    suspend fun getApod(dataInicial : Calendar, dataFinal : Calendar) : List<Apod>
}
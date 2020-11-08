package com.example.iterativechallenger.domain.repositories

import com.example.iterativechallenger.domain.entities.Apod
import java.util.*

interface ApodRepository {

    suspend fun getApod(dataFinal : Calendar, dataInicial : Calendar) : List<Apod>
}
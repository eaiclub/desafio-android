package com.example.iterativechallenger.data.mappers

import com.example.iterativechallenger.core.utils.Constantes
import com.example.iterativechallenger.data.models.ApodDataResponse
import com.example.iterativechallenger.domain.entities.Apod
import java.text.SimpleDateFormat
import java.util.*

class ApodDataResponseToApodMapper : Mapper<List<ApodDataResponse>, List<Apod>> {
    override fun map(input: List<ApodDataResponse>): List<Apod> {

        val sdf = SimpleDateFormat(Constantes.FORMATO_ANO_MES_DIA, Locale.US)

        return input.map {
            Apod(
                it.title,
                it.copyright,
                sdf.parse(it.date)!!,
                it.explanation,
                it.url,
                it.hdurl
            )
        }
    }

}
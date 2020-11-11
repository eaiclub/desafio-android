package com.example.iterativechallenger.data.mapper

import com.example.iterativechallenger.core.utils.Constantes
import com.example.iterativechallenger.data.mappers.ApodDataResponseToApodMapper
import com.example.iterativechallenger.data.models.ApodDataResponse
import com.example.iterativechallenger.domain.entities.Apod
import io.mockk.coEvery
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class ApodDataResponseToApodMapperTest {

    private lateinit var tApod: Apod
    private lateinit var tApodDataResponse: ApodDataResponse
    private lateinit var tApodDataResponseToApodMapper: ApodDataResponseToApodMapper

    @Before
    fun setup(){

        tApodDataResponseToApodMapper = ApodDataResponseToApodMapper()

        val sdf = SimpleDateFormat(Constantes.FORMATO_ANO_MES_DIA, Locale.US)
        val date = sdf.parse("2020-11-10")!!
        tApod = Apod(
                title = "titulo",
                copyright = "copyright",
                date = date,
                explanation = "explanation",
                url = "url",
                hdUrl = "hdUrl",
                mediaType = "mediaType"
        )

        tApodDataResponse = ApodDataResponse(
                copyright = "copyright",
                date = "2020-11-10",
                explanation = "explanation",
                hdurl = "hdUrl",
                media_type = "mediaType",
                service_version = "",
                title = "titulo",
                url = "url"
        )
    }

    @Test
    fun`map sucess`(){
        val expect = listOf(tApod)

        val result = tApodDataResponseToApodMapper.map(listOf(tApodDataResponse))

        assertEquals(expect, result)
    }
}
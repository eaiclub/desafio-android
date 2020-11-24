package com.example.iterativechallenger.data.repositories

import com.example.iterativechallenger.core.utils.Constantes
import com.example.iterativechallenger.data.data_sources.ApodRemoteDataSource
import com.example.iterativechallenger.data.mappers.ApodDataResponseToApodMapper
import com.example.iterativechallenger.data.models.ApodDataResponse
import com.example.iterativechallenger.domain.entities.Apod
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class ApodRepositoryImplTest {

    private lateinit var apodRepositoryImpl: ApodRepositoryImpl
    private lateinit var remoteDataSource: ApodRemoteDataSource
    private lateinit var apodDataResponseToApodMapper: ApodDataResponseToApodMapper
    private lateinit var tApod : Apod
    private lateinit var tApodDataResponse: ApodDataResponse
    private val dataInicio = Calendar.getInstance()
    private val dataFim = Calendar.getInstance()
    private lateinit var dataInicioString : String
    private lateinit var dataFimString : String

    @Before
    fun setup(){
        remoteDataSource = mockk()
        apodDataResponseToApodMapper = mockk()
        apodRepositoryImpl = ApodRepositoryImpl(remoteDataSource, apodDataResponseToApodMapper)

        val sdf = SimpleDateFormat(Constantes.FORMATO_ANO_MES_DIA, Locale.US)
        val date = sdf.parse("2020-11-10")!!
        dataInicioString = sdf.format(dataInicio.time)
        dataFimString = sdf.format(dataFim.time)

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
    fun `getListApod - should return apod data when response is successfull`() = runBlocking{
        // arrange
        coEvery { remoteDataSource.getApod(any(), any()) } returns listOf(tApodDataResponse)
        every { apodDataResponseToApodMapper.map(any()) } returns listOf(tApod)
        // act
        val result = async {apodRepositoryImpl.getApod(dataInicio, dataFim)}.await()
        // assert
        assertEquals(listOf(tApod), result)
        coVerify(exactly = 1) {remoteDataSource.getApod(dataInicioString, dataFimString) }
    }

}
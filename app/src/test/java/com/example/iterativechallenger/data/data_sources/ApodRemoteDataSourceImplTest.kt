package com.example.iterativechallenger.data.data_sources

import com.example.iterativechallenger.BuildConfig
import com.example.iterativechallenger.core.exceptions.BadRequestThrowable
import com.example.iterativechallenger.core.exceptions.InvalidApiKeyThrowable
import com.example.iterativechallenger.core.exceptions.ResourceNotFoundThrowable
import com.example.iterativechallenger.data.api.ApiService
import com.example.iterativechallenger.data.models.ApodDataResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import retrofit2.Response
import java.util.*

class ApodRemoteDataSourceImplTest {

    private lateinit var mockApodApi : ApiService
    private lateinit var apodRemoteDataSourceImpl: ApodRemoteDataSourceImpl

    private val dataInicial = "2020-11-10"
    private val dataFinal = "2020-11-08"

    @get:Rule
    var thrown = ExpectedException.none()

    @Before
    fun setup(){
        mockApodApi = mockk()
        apodRemoteDataSourceImpl = ApodRemoteDataSourceImpl(mockApodApi)
    }

    @Test
    fun `getApods - should return success when api returns success`() = runBlocking {

        val expect = mockk<List<ApodDataResponse>>()

        coEvery { mockApodApi.getApods(any(), any(), any()) } returns Response.success(expect)

        val result = apodRemoteDataSourceImpl.getApod(dataInicial, dataFinal)

        assertEquals(expect, result)
        coVerify { mockApodApi.getApods(BuildConfig.API_KEY, dataInicial, dataFinal) }
    }


    @Test
    fun`getApods - should throw invalidApiKeyException when api returns 400`() {

        coEvery { mockApodApi.getApods(any(), any(), any()) } returns Response.error(400, mockk())
        thrown.expect(BadRequestThrowable::class.java)

        runBlocking {
            apodRemoteDataSourceImpl.getApod(dataInicial, dataFinal)
        }
    }

    @Test
    fun`getApods - should throw invalidApiKeyException when api returns 401`() {

        coEvery { mockApodApi.getApods(any(), any(), any()) } returns Response.error(401, mockk())
        thrown.expect(InvalidApiKeyThrowable::class.java)

        runBlocking {
            apodRemoteDataSourceImpl.getApod(dataInicial, dataFinal)
        }
    }

    @Test
    fun`getApods - should throw invalidApiKeyException when api returns 404`(){
        coEvery { mockApodApi.getApods(any(), any(), any()) } returns Response.error(404, mockk())
        thrown.expect(ResourceNotFoundThrowable::class.java)

        runBlocking {
            apodRemoteDataSourceImpl.getApod(dataInicial, dataFinal)
        }
    }

}
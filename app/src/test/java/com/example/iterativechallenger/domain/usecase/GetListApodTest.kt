package com.example.iterativechallenger.domain.usecase

import com.example.iterativechallenger.domain.entities.Apod
import com.example.iterativechallenger.domain.repositories.ApodRepository
import com.example.iterativechallenger.domain.usecases.GetListApod
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*

class GetListApodTest {

    private lateinit var mockApodRepository: ApodRepository
    private lateinit var getListApodUseCase : GetListApod
    private lateinit var apodListMock : List<Apod>

    private val dataInicio = Calendar.getInstance()
    private val dataFim = Calendar.getInstance()

    @Before
    fun setup(){
        mockApodRepository = mockk()
        getListApodUseCase = GetListApod(mockApodRepository)
        apodListMock = listOf(mockk(), mockk())
    }

    @Test
    fun `should return apods from the repository`() = runBlocking {
        // arrange
        coEvery { mockApodRepository.getApod(any(), any()) } returns apodListMock
        // act
        val result = async { getListApodUseCase(dataInicio, dataFim) }.await()
        // assert
        Assert.assertEquals(apodListMock, result)
        coVerify(exactly = 1) {
            mockApodRepository.getApod(dataInicio, dataFim)
        }
    }
}
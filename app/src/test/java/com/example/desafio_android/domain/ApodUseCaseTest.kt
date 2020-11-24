package com.example.desafio_android.domain

import com.example.desafio_android.common.domain.models.NasaApod
import com.example.desafio_android.common.domain.models.NasaApodResponse
import com.example.desafio_android.common.domain.repository.NasaRepository
import com.example.desafio_android.common.domain.usecase.ApodUseCase
import com.example.desafio_android.util.Resource
import com.example.desafio_android.util.Status
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ApodUseCaseTest {

    val mockedRepo: NasaRepository = mockk()
    val mockedUseCase: ApodUseCase = ApodUseCase(mockedRepo)

    val offset: Int = 0

    private lateinit var mockedResponse: NasaApodResponse
    private lateinit var mockedNasaApod: NasaApod

    @Before
    fun start(){
        mockedResponse = NasaApodResponse(
                copyright = "Jen Scott",
                date = "2020-11-19",
                explanation = "Leaving planet Earth for a moment",
                hdurl = "https://apod.nasa.gov/apod/image/2011/spacex-crew-1-JenScottPhotography-11.jpg",
                media_type = "image",
                service_version = "v1",
                title = "Crew-1 Mission Launch Streak",
                url = "https://apod.nasa.gov/apod/image/2011/spacex-crew-1-JenScottPhotography-11_1050.jpg"
        )

        mockedNasaApod = NasaApod(
                copyright = "copyright",
                date = "2020-11-19",
                explanation = "Leaving planet Earth for a moment",
                hdUrl = "https://apod.nasa.gov/apod/image/2011/spacex-crew-1-JenScottPhotography-11.jpg",
                mediaType = "image",
                serviceVersion = "v1",
                title = "Crew-1 Mission Launch Streak",
                url = "https://apod.nasa.gov/apod/image/2011/spacex-crew-1-JenScottPhotography-11_1050.jpg"
        )
    }


    @Test
    fun test_usecase_retrieves_expected_data() = run {
        coEvery { mockedRepo.getApodList(any(), any()) } returns Resource(Status.SUCCESS, listOf(mockedResponse), null)

        runBlocking {
            val response = mockedUseCase.getApodList(offset)
            Assert.assertTrue("Tipo incorreto", response.data is List<NasaApod>)
        }
    }


}
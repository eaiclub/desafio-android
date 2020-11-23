package com.example.desafio_android.domain

import com.example.desafio_android.common.Resource
import com.example.desafio_android.common.ResponseHandler
import com.example.desafio_android.common.domain.models.NasaApodResponse
import com.example.desafio_android.common.domain.repository.NasaRepository
import com.example.desafio_android.data.remote.provideNasaApi
import com.example.desafio_android.data.remote.provideRetrofit
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class NasaRepositoryTest {

    private lateinit var nasaRepo: NasaRepository

    val initialDate: String = "2020-11-19"
    val finalDate: String = "2020-11-19"

    @Before
    fun start(){
        nasaRepo = NasaRepository(provideNasaApi(provideRetrofit()), ResponseHandler())
    }

    @Test
    fun test_get_apod_list_retrieves_expected_data_type() = run {
        var dataReceived: Resource<List<NasaApodResponse>>? = null

        runBlocking {
            dataReceived = nasaRepo.getApodList(initialDate, finalDate)
        }

        Assert.assertNotNull("Dado está nulo", dataReceived?.data)
        Assert.assertTrue("Tipo incorreto", dataReceived?.data is List<NasaApodResponse>)
    }

}
package com.example.desafio_android.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.desafio_android.common.domain.models.NasaApod
import com.example.desafio_android.common.domain.usecase.ApodUseCase
import com.example.desafio_android.ui.NasaViewModel
import com.example.desafio_android.util.Resource
import com.example.desafio_android.util.Status
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*

@RunWith(JUnit4::class)
class NasaViewModelTest {

    lateinit var mockedNasaViewModel: NasaViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var mockedUseCase: ApodUseCase

    val initialDate = Calendar.getInstance()
    val finalDate = Calendar.getInstance()

    @Before
    fun start(){
        mockedUseCase = mockk()
    }



    @Test
    fun test_nasa_view_model_livedata_populates_expected_value() = run {
        mockedNasaViewModel = NasaViewModel(mockedUseCase)

        coEvery { mockedUseCase.getApodList(any(), any()) } returns Resource(Status.SUCCESS, listOf<NasaApod>(mockk()), null)
        mockedNasaViewModel.getApodLiveData().observeForever {  }

        runBlocking{  mockedNasaViewModel.loadApodList(initialDate, finalDate) }

        assert( mockedNasaViewModel.getApodLiveData().value != null)
        assert(mockedNasaViewModel.getApodLiveData().value!!.status == Status.SUCCESS)
    }
}
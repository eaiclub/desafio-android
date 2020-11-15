package com.jess.eaiclubnasa.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import com.jess.eaiclubnasa.model.ApodResult
import com.jess.eaiclubnasa.repository.ApodRepository
import com.jess.eaiclubnasa.viewmodel.event.ApodEvent
import com.jess.eaiclubnasa.viewmodel.interactor.ApodInteractor
import com.jess.eaiclubnasa.viewmodel.state.ApodState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class ApodViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private var repository = Mockito.mock(ApodRepository::class.java)

    @Before
    fun up() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        testCoroutineDispatcher.cleanupTestCoroutines()
        Dispatchers.resetMain()
    }

    @Test
    fun testGetListApod() = testCoroutineDispatcher.runBlockingTest {
        val viewModel = ApodViewModel(testCoroutineDispatcher, repository)
        testCoroutineDispatcher.pauseDispatcher()

        Mockito.doReturn(getApodResponse()).`when`(repository)
            .getApod("2020-11-14")

        Mockito.doReturn(getApodResponse()).`when`(repository)
            .getApod("2020-11-13")

        Mockito.doReturn(getApodResponse()).`when`(repository)
            .getApod("2020-11-12")

        Mockito.doReturn(getApodResponse()).`when`(repository)
            .getApod("2020-11-11")

        Mockito.doReturn(getApodResponse()).`when`(repository)
            .getApod("2020-11-10")

        Mockito.doReturn(getApodResponse()).`when`(repository)
            .getApod("2020-11-09")

        Mockito.doReturn(getApodResponse()).`when`(repository)
            .getApod("2020-11-08")

        viewModel.interpret(ApodInteractor.GetListApod("2020-11-14"))

        assertEquals(viewModel.viewEvent.value, ApodEvent.Loading(true))

        testCoroutineDispatcher.resumeDispatcher()

        assertEquals(ApodState.ApodListSuccess(getListApod()), viewModel.viewState.value)
    }

    private fun getApodResponse(): ApodResult {
        return Gson().fromJson(
            "\n" +
                    "  {\n" +
                    "    \"date\": \"1996-08-14\",\n" +
                    "    \"explanation\": \"Details of the crazed cracks criss-crossing Europa's frozen surface are apparent in this mosaic of the Galileo spacecraft's latest images of Jupiter's ice-covered moon. Curious white stripes, also seen by Voyager, are clearly visible marking the center of the wide dark fractures.  One theory suggests that \\\"dirty geysers\\\" erupting along the cracks deposited darker material followed by a flow of cleaner water ice which produced the stripe. The above image also shows an impact crater about 18.5 miles in diameter surrounded by white ejecta (lower left) and a curving x-pattern at bottom left which suggests fractures between icy plates filled with slush frozen in place.  Is there now or was there ever liquid water beneath Europa's surface? These latest results still hold out that possibility -- and so the possibility of life. Europa, along with Mars and Saturn's moon Titan is considered to be one of the few places in our Solar System, beyond Earth, where primitive life forms could have developed. Galileo's close flyby of this tantalizing moon is scheduled for December of this year.\",\n" +
                    "    \"hdurl\": \"https://apod.nasa.gov/apod/image/europa1_gal_big.jpg\",\n" +
                    "    \"media_type\": \"image\",\n" +
                    "    \"service_version\": \"v1\",\n" +
                    "    \"title\": \"Galileo Explores Europa\",\n" +
                    "    \"url\": \"https://apod.nasa.gov/apod/image/europa11_gal.gif\"\n" +
                    "  }\n" +
                    "\n" +
                    "\n",
            ApodResult::class.java
        )
    }

    private fun getListApod(): MutableList<ApodResult> {
        return mutableListOf(
            getApodResponse(),
            getApodResponse(),
            getApodResponse(),
            getApodResponse(),
            getApodResponse(),
            getApodResponse()
        )
    }
}
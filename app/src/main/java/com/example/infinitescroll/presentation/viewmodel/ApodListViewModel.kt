package com.example.infinitescroll.presentation.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import com.example.infinitescroll.data.model.Apod
import com.example.infinitescroll.domain.usecase.GetApodNetworkUseCase
import com.example.infinitescroll.domain.usecase.GetApodUseCase
import java.util.*

class ApodListViewModel @ViewModelInject internal constructor(
        private val getApodNetworkUseCase: GetApodNetworkUseCase,
        private val getApodUseCase: GetApodUseCase
) : ViewModel() {

    companion object {
        const val PAGESIZE = 30
    }

    fun getApods(): DataSource.Factory<Int, Apod> {
        return getApodUseCase.execute()
    }

    suspend fun loadApods(calendar : Calendar?) {
        getApodNetworkUseCase.execute(calendar)
    }
}
package com.example.infinitescroll.presentation.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import com.example.infinitescroll.data.model.Apod
import com.example.infinitescroll.domain.usecase.GetApodNetworkUseCase
import com.example.infinitescroll.domain.usecase.GetApodUseCase
import java.lang.Exception
import java.util.*

class ApodListViewModel @ViewModelInject internal constructor(
        private val getApodNetworkUseCase: GetApodNetworkUseCase,
        private val getApodUseCase: GetApodUseCase
) : ViewModel() {

    private val errorMessage = MutableLiveData<Boolean>().apply { value = false }

    fun getErrorMessage() : LiveData<Boolean> = errorMessage

    companion object {
        const val PAGESIZE = 30
    }

    fun getApods(): DataSource.Factory<Int, Apod> {
        return getApodUseCase.execute()
    }

    suspend fun loadApods(calendar : Calendar?) {
        try {
            getApodNetworkUseCase.execute(calendar)
        } catch (e : Exception){
            errorMessage.postValue(true)
        }
    }
}
package com.example.desafio_android.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafio_android.common.domain.models.NasaApod
import com.example.desafio_android.common.domain.usecase.ApodUseCase
import com.example.desafio_android.util.Resource
import kotlinx.coroutines.launch
import java.util.*

class NasaViewModel(private val useCase: ApodUseCase): ViewModel() {

    private var apodLiveData = MutableLiveData<Resource<List<NasaApod>>>()

    fun getApodLiveData() = apodLiveData

    suspend fun loadApodList(initialDate: Calendar, finalDate: Calendar){
        viewModelScope.launch {
            apodLiveData.postValue(Resource.loading(null))
            apodLiveData.postValue(useCase.getApodList(initialDate, finalDate))
        }
    }
}
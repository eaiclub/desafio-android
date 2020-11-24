package com.example.desafio_android.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafio_android.common.domain.models.NasaApod
import com.example.desafio_android.common.domain.usecase.ApodUseCase
import com.example.desafio_android.util.DEFAULT_OFFSET
import com.example.desafio_android.util.Resource
import kotlinx.coroutines.launch

class NasaViewModel(private val useCase: ApodUseCase): ViewModel() {

    private var apodLiveData = MutableLiveData<Resource<List<NasaApod>>>()

    private val mutableApodList = mutableListOf<NasaApod>()

    fun getApodLiveData() = apodLiveData

    fun loadApodList(offset: Int = DEFAULT_OFFSET){
        viewModelScope.launch {
            apodLiveData.postValue(Resource.loading(null))
            val response = useCase.getApodList(offset)
            apodLiveData.postValue(response)
            mutableApodList.addAll(response.data ?: listOf())
        }
    }

    fun getApodListFromMemory() = mutableApodList

    fun loadApodsFromMemory() {
        apodLiveData.value = (Resource.success(mutableApodList))
    }

}
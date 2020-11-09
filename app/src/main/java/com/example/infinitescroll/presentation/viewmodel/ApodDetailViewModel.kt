package com.example.infinitescroll.presentation.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.infinitescroll.data.model.Apod

class ApodDetailViewModel @ViewModelInject internal constructor() : ViewModel() {

    val apod = MutableLiveData<Apod>().apply { value = null }

    fun bound(apod : Apod) {
        this.apod.postValue(apod)
    }
}
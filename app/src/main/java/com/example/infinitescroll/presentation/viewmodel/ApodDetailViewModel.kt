package com.example.infinitescroll.presentation.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.infinitescroll.data.model.Apod

class ApodDetailViewModel @ViewModelInject internal constructor() : ViewModel() {

    val apod = MutableLiveData<Apod>().apply { value = null }
    private val openDialog = MutableLiveData<String?>().apply { value = null }
    private val openUrl = MutableLiveData<String?>().apply { value = null }

    fun getOpenDialog() : LiveData<String?> = openDialog

    fun getOpenUrl() : LiveData<String?> = openUrl

    fun bound(apod : Apod) {
        this.apod.value = apod
    }

    fun getApodImage() : String? {
        if(apod.value!!.hasImage())
            return apod.value!!.url
        return null
    }

    /**
     * Decide if we are dealing with image or video
     * Since video can come from different sources (youtube, vimeo, etc) we open it in the browser
    * */
    fun onImageClicked(){
        if(apod.value!!.hasImage())
            openDialog.postValue(apod.value!!.url)
        else
            openUrl.postValue(apod.value!!.url)
    }

}
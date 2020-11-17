package com.lucasdonato.nasa.presentation.home.presenter

import com.lucasdonato.nasa.data.model.Apod
import com.lucasdonato.nasa.data.useCase.ApodUseCase
import com.lucasdonato.nasa.mechanism.livedata.MutableLiveDataResource
import com.lucasdonato.nasa.mechanism.livedata.Resource
import com.lucasdonato.nasa.presentation.AppApplication
import com.lucasdonato.nasa.presentation.base.presenter.BasePresenter

class HomePresenter(
    private val apodUseCase: ApodUseCase
) : BasePresenter() {

    val getListLiveData = MutableLiveDataResource<List<Apod>>()

    fun getList(start_date: String, end_date: String) = runCoroutine {
        getListLiveData.postValue(Resource.loading())
        apodUseCase.getData(start_date, end_date)?.let {
            getListLiveData.postValue(Resource.success(it))
        } ?: getListLiveData.postValue(Resource.error())
    } onError {
        getListLiveData.postValue(
            Resource.error(AppApplication.context?.getString(it.errorCode.stringCode))
        )
    }

}












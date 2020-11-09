package com.example.iterativechallenger.presentation.pages.apods

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iterativechallenger.core.utils.Response
import com.example.iterativechallenger.domain.entities.Apod
import com.example.iterativechallenger.domain.usecases.GetListApod
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel(
    private val getListApodUseCase: GetListApod
) : ViewModel() {

    private var diaFimRange = 0
    private var diaInicioRange = -10
    private val dataInicio = Calendar.getInstance()
    private val dataFim = Calendar.getInstance()
    private var apodList = arrayListOf<Apod>()

    private val response = MutableLiveData<Response>()

    fun getApods(){
        response.postValue(Response.loading())

        dataInicio.add(Calendar.DATE, diaInicioRange)
        dataFim.add(Calendar.DATE, diaFimRange)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                    val result = getListApodUseCase(dataInicio, dataFim)
                    apodList.addAll(result)
                    response.postValue(Response.success(result))
                }
            catch (t : Throwable){
                response.postValue(Response.error(t))
            }
        }
    }

    fun loadMoreApods(){
        diaFimRange = diaInicioRange
        getApods()
    }

    fun getApodList() = apodList

    fun response(): MutableLiveData<Response> {
        return response
    }
}
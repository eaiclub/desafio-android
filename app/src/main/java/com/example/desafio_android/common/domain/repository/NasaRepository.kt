package com.example.desafio_android.common.domain.repository

import com.example.desafio_android.util.API_KEY
import com.example.desafio_android.util.Resource
import com.example.desafio_android.util.ResponseHandler
import com.example.desafio_android.common.domain.models.NasaApodResponse
import com.example.desafio_android.data.remote.INasaApiService

class NasaRepository(private val api: INasaApiService, private val responseHandler: ResponseHandler) {

  suspend fun getApodList(initialDate: String, finalDate: String): Resource<List<NasaApodResponse>> {
      return try {
          val response = api.getApodsList(API_KEY, initialDate, finalDate)
          responseHandler.handleSuccess(response)
      } catch (e: Throwable){
          responseHandler.handleThrowable(e)
      }
    }


}
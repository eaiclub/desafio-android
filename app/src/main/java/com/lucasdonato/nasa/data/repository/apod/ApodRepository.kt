package com.lucasdonato.nasa.data.repository

import android.content.Context
import android.widget.Toast
import com.lucasdonato.nasa.data.model.Apod
import com.lucasdonato.nasa.data.remote.model.Service
import com.lucasdonato.nasa.data.remote.model.WebService
import com.lucasdonato.nasa.mechanism.API_KEY
import com.lucasdonato.nasa.mechanism.network.NetworkUtils
import com.lucasdonato.nasa.presentation.AppApplication.Companion.context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApodRepository(context: Context) {

    suspend fun getApodDate() = withContext(Dispatchers.IO) {
        try {
            return@withContext Service.service.getList()
        } catch (exception: Exception) {
            val error = exception.message
            return@withContext error
        }
    }

}
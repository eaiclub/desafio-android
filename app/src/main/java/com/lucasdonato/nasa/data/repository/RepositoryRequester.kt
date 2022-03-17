package com.lucasdonato.nasa.data.repository

import com.google.gson.Gson
import com.lucasdonato.nasa.data.repository.type.WsError
import okhttp3.ResponseBody
import retrofit2.Response

fun <T : Any> performRequest(response: Response<T>, verifyBody: Boolean = true): Any {
    try {
        return if (response.isSuccessful) {
            if (verifyBody) {
                response.body()?.let {
                    it
                } ?: treatError(response.errorBody())
            } else {
                true
            }
        } else {
            treatError(response.errorBody())
        }
    } catch (e: Exception) {
        when (e) {
            /**
             * Include more cases if needed
             */
            is ErrosException -> throw ErrosException(
                e.errorCode
            )
            else -> throw ErrosException(ErrorCode.GENERIC_ERROR)
        }
    }
}

fun treatError(errorBody: ResponseBody? = null) {
    errorBody?.string()?.let { error ->
        /**
         * Customize error class depending project
         * Each project has it's own error structure, customize method to adapt
         */
        Gson().fromJson(error, WsError::class.java)?.let { wsError ->
            throw ErrosException(ErrorCode.fromString(wsError.error))
        }
    } ?: throw ErrosException(ErrorCode.GENERIC_ERROR)
}
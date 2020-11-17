package br.com.versa.data.repository

import br.com.versa.data.WsError
import com.google.gson.Gson
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
            is MBLabsException -> throw MBLabsException(
                e.errorCode
            )
            else -> throw MBLabsException(ErrorCode.GENERIC_ERROR)
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
            throw MBLabsException(ErrorCode.fromString(wsError.error))
        }
    } ?: throw MBLabsException(ErrorCode.GENERIC_ERROR)
}
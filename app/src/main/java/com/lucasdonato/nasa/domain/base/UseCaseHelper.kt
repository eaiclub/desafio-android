package com.lucasdonato.nasa.domain.base

import android.util.Log
import com.lucasdonato.nasa.data.repository.ErrorCode
import com.lucasdonato.nasa.data.repository.ErrosException

/**
 * Use this method for execute requests WS/DB inside methods on UseCase
 */
fun <T : Any?> runMethod(block: () -> T): T {
    try {
        return block()
    } catch (e: Exception) {
        Log.e("UseCaseHelper", e.message ?: "")
        when (e) {
            is ErrosException -> throw ErrosException(e.errorCode)
            else -> throw ErrosException(ErrorCode.GENERIC_ERROR)
        }
    }
}

suspend fun <T : Any?> runSuspend(run: suspend () -> T): T {
    try {
        return run()
    } catch (e: Exception) {
        Log.e("UseCaseHelper", e.message ?: "")
        when (e) {
            is ErrosException -> throw ErrosException(e.errorCode)
            else -> throw ErrosException(ErrorCode.GENERIC_ERROR)
        }
    }
}
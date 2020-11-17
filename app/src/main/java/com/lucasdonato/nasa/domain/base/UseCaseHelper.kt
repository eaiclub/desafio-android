package br.com.versa.domain.usecase.base

import android.util.Log
import br.com.versa.data.repository.ErrorCode
import br.com.versa.data.repository.MBLabsException


/**
 * Use this method for execute requests WS/DB inside methods on UseCase
 */
fun <T : Any?> runMethod(block: () -> T): T {
    try {
        return block()
    } catch (e: Exception) {
        Log.e("UseCaseHelper", e.message ?: "")
        when (e) {
            is MBLabsException -> throw MBLabsException(e.errorCode)
            else -> throw MBLabsException(ErrorCode.GENERIC_ERROR)
        }
    }
}

suspend fun <T : Any?> runSuspend(run: suspend () -> T): T {
    try {
        return run()
    } catch (e: Exception) {
        Log.e("UseCaseHelper", e.message ?: "")
        when (e) {
            is MBLabsException -> throw MBLabsException(e.errorCode)
            else -> throw MBLabsException(ErrorCode.GENERIC_ERROR)
        }
    }
}
package br.com.versa.controller.executor

import br.com.versa.data.repository.MBLabsException
import kotlinx.coroutines.Deferred


interface ExecutorCoroutineScope {
    fun cancelJobs()
    fun runCoroutine(run: suspend () -> Unit): ConcurrencyContinuation = ConcurrencyContinuation(run)
    suspend fun <T> runAsync(run: suspend () -> T): Deferred<T>
    infix fun ConcurrencyContinuation.onError(onError: (MBLabsException) -> Unit)
}

inline class ConcurrencyContinuation(val action: suspend () -> Unit)
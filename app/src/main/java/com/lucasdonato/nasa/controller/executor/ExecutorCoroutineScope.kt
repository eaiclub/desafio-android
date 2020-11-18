package com.lucasdonato.nasa.controller.executor

import com.lucasdonato.nasa.data.repository.ErrosException
import kotlinx.coroutines.Deferred

interface ExecutorCoroutineScope {
    fun cancelJobs()
    fun runCoroutine(run: suspend () -> Unit): ConcurrencyContinuation = ConcurrencyContinuation(run)
    suspend fun <T> runAsync(run: suspend () -> T): Deferred<T>
    infix fun ConcurrencyContinuation.onError(onError: (ErrosException) -> Unit)
}

inline class ConcurrencyContinuation(val action: suspend () -> Unit)
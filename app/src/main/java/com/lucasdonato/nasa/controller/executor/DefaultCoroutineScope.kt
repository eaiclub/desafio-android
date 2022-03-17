package com.lucasdonato.nasa.controller.executor

import com.lucasdonato.nasa.data.repository.ErrosException
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class DefaultCoroutineScope : ExecutorCoroutineScope, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + Job()

    override fun cancelJobs() {
        coroutineContext.cancel()
    }

    override infix fun ConcurrencyContinuation.onError(onError: (ErrosException) -> Unit) {
        initCoroutine(this.action, onError)
    }

    override suspend fun <T> runAsync(run: suspend () -> T) = async { run() }

    private fun initCoroutine(run: suspend () -> Unit, onError: (ErrosException) -> Unit = {}) =
        launch {
            try {
                run()
            } catch (e: ErrosException) {
                onError(e)
            }
        }
}

fun getCoroutineScope(): DefaultCoroutineScope = DefaultCoroutineScope()
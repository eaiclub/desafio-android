package br.com.versa.controller.executor


import br.com.versa.data.repository.MBLabsException
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class DefaultCoroutineScope : ExecutorCoroutineScope, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + Job()

    override fun cancelJobs() {
        coroutineContext.cancel()
    }

    override infix fun ConcurrencyContinuation.onError(onError: (MBLabsException) -> Unit) {
        initCoroutine(this.action, onError)
    }

    override suspend fun <T> runAsync(run: suspend () -> T) = async { run() }

    private fun initCoroutine(run: suspend () -> Unit, onError: (MBLabsException) -> Unit = {}) = launch {
        try {
            run()
        } catch (e: MBLabsException) {
            onError(e)
        }
    }
}

fun getCoroutineScope(): DefaultCoroutineScope = DefaultCoroutineScope()
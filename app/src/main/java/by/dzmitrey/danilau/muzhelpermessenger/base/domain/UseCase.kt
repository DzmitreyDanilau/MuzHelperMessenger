package by.dzmitrey.danilau.muzhelpermessenger.base.domain

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseUseCase<in Params> : CoroutineScope {

    private val parentJob = SupervisorJob()
    private val mainDispatcher = Dispatchers.Main
    private val backgroundDispatcher = Dispatchers.Default

    override val coroutineContext: CoroutineContext
        get() = parentJob + mainDispatcher

    protected abstract suspend fun run(params: Params)

    /**
     * By overriding invoke, we allow use cases to be called as "invoking"
     */
    operator fun invoke(params: Params) {
        launch {
            withContext(backgroundDispatcher) { run(params) }
        }
    }

    protected fun <T> startAsync(block: suspend () -> T): Deferred<T> = async(parentJob) {
        block()
    }

    /**
     * Should be called when use-case owner is destroyed
     * This will ensure coroutine is cancelled if it's still running some tasks
     */
    fun clear() {
        parentJob.cancel()
    }
}


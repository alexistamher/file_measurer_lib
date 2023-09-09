package com.spookybrain.kmm

import kotlinx.coroutines.*

actual abstract class SharedViewModel {
    actual val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    actual fun dispose() {
        coroutineScope.cancel()
        onCleared()
    }

    protected actual open fun onCleared() {

    }

}
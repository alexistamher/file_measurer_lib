package com.spookybrain.kmm

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

actual abstract class SharedViewModel {
    actual val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    actual fun dispose() {
        coroutineScope.cancel()
        onCleared()
    }

    protected actual open fun onCleared() {

    }

}
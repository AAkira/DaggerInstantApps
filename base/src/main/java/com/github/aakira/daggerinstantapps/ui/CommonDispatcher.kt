package com.github.aakira.daggerinstantapps.ui

abstract class CommonDispatcher(val provider: DispatcherProvider) {

    fun errors() = provider.errorDispatcher.errorProcessor

    fun onError(error: ErrorEvent) = provider.errorDispatcher.onError(error)

    fun onError(action: Any, cause: Throwable, extras: Any? = null, recoverAction: (() -> Unit)? = null) =
            provider.errorDispatcher.onError(action, cause, extras, recoverAction)

}
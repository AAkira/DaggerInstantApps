package com.github.aakira.daggerinstantapps.ui

import com.github.aakira.daggerinstantapps.util.ext.isNetworkConnectionError
import io.reactivex.subjects.PublishSubject

class ErrorDispatcher {

    val errorProcessor = PublishSubject.create<ErrorEvent>().toSerialized()

    fun onError(error: ErrorEvent) = errorProcessor.onNext(error)

    fun onError(action: Any, cause: Throwable, extras: Any? = null, recoverAction: (() -> Unit)? = null) = errorProcessor.onNext(
            ErrorEvent(action, cause,
                    when {
                        cause.isNetworkConnectionError() -> ErrorEvent.Kind.NETWORK
                        else -> ErrorEvent.Kind.OTHER
                    }, extras, recoverAction)
    )
}
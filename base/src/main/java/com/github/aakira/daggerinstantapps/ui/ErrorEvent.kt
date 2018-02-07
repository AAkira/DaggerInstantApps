package com.github.aakira.daggerinstantapps.ui

data class ErrorEvent(val action: Any,
                      val cause: Throwable? = null,
                      val kind: Kind = ErrorEvent.Kind.OTHER,
                      val extras: Any? = null,
                      val recoverAction: (() -> Unit)? = null
) {

    enum class Kind {
        OTHER,
        NETWORK,
    }
}

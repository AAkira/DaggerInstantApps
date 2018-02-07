package com.github.aakira.daggerinstantapps.util.ext

import com.github.aakira.daggerinstantapps.data.api.exception.RetrofitException

fun Throwable.isNetworkConnectionError() = (this is RetrofitException && this.kind == RetrofitException.Kind.NETWORK)
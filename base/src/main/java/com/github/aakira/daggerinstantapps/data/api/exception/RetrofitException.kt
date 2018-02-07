package com.github.aakira.daggerinstantapps.data.api.exception

import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException

class RetrofitException internal constructor(
        val kind: RetrofitException.Kind,
        message: String? = null,
        exception: Throwable? = null,
        val url: String? = null,
        val response: Response<out Any>? = null,
        val retrofit: Retrofit? = null) : RuntimeException(message, exception) {

    enum class Kind {
        NETWORK,
        HTTP,
        UNEXPECTED
    }

    companion object {
        fun httpError(response: Response<out Any>, retrofit: Retrofit) =
                RetrofitException(Kind.HTTP, "${response.code()} ${response.message()}", null,
                        response.raw().request().url().toString(), response, retrofit)

        fun networkError(exception: IOException) =
                RetrofitException(Kind.NETWORK, exception.message, exception)

        fun unexpectedError(exception: Throwable) =
                RetrofitException(Kind.UNEXPECTED, exception.message, exception)
    }
}

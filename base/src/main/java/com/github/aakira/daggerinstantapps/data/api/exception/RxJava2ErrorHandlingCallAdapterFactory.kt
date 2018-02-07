package com.github.aakira.daggerinstantapps.data.api.exception

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.Function
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.IOException
import java.lang.reflect.Type

object RxJava2ErrorHandlingCallAdapterFactory : CallAdapter.Factory() {

    fun get() = this

    private val original = RxJava2CallAdapterFactory.create()

    override fun get(returnType: Type, annotations: Array<out Annotation>, retrofit: Retrofit): CallAdapter<*, *> =
            RxCallAdapterWrapper(retrofit, original.get(returnType, annotations, retrofit))

    class RxCallAdapterWrapper<R>(private val retrofit: Retrofit, private val wrappedCallAdapter: CallAdapter<R, *>?)
        : CallAdapter<R, Any> {

        override fun responseType() = wrappedCallAdapter?.responseType()

        override fun adapt(call: Call<R>?): Any {
            val adapt = wrappedCallAdapter?.adapt(call)
            return when (adapt) {
                is Flowable<out Any> -> adapt.onErrorResumeNext(Function { Flowable.error(asRetrofitException(it)) })
                is Observable<out Any> -> adapt.onErrorResumeNext(Function { Observable.error(asRetrofitException(it)) })
                is Single<out Any> -> adapt.onErrorResumeNext(Function { Single.error(asRetrofitException(it)) })
                is Maybe<out Any> -> adapt.onErrorResumeNext(Function { Maybe.error(asRetrofitException(it)) })
                is Completable -> adapt.onErrorResumeNext(Function { Completable.error(asRetrofitException(it)) })
                else -> throw UnsupportedOperationException()
            }
        }

        private fun asRetrofitException(throwable: Throwable) = when (throwable) {
            is HttpException -> RetrofitException.httpError(throwable.response(), retrofit)
            is IOException -> RetrofitException.networkError(throwable)
            else -> RetrofitException.unexpectedError(throwable)
        }
    }
}
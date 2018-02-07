package com.github.aakira.daggerinstantapps.data

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.github.aakira.daggerinstantapps.BuildConfig
import com.github.aakira.daggerinstantapps.data.api.exception.RxJava2ErrorHandlingCallAdapterFactory
import com.github.aakira.daggerinstantapps.di.ApiModules
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [ApiModules::class])
internal object DebugDataModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideMoshi() = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @JvmStatic
    @Singleton
    @Provides
    fun providesOkHttp(): OkHttpClient = OkHttpClient.Builder()
            .run {
                BuildConfig.GITHUB_TOKEN.let {
                    if (it.isNotBlank()) {
                        addInterceptor { it.proceed(it.request().newBuilder().addHeader("Authorization", "token " + BuildConfig.GITHUB_TOKEN).build()) }
                    } else this
                }
            }
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .addInterceptor(HttpLoggingInterceptor()
                    .apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()

    @JvmStatic
    @Singleton
    @Provides
    fun provideRetrofit(oktHttpClient: OkHttpClient, moshi: Moshi): Retrofit = Retrofit.Builder()
            .client(oktHttpClient)
            .baseUrl("https://api.github.com")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2ErrorHandlingCallAdapterFactory.get())
            .build()
}
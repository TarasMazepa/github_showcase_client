package com.showcase.okhttp

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
object OkHttpModule {
    @Provides
    fun provideInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor {
                it.proceed(
                    it.request().newBuilder().addHeader(
                        "Authorization",
                        " Bearer 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"
                    ).build()
                )
            }.build()
    }
}
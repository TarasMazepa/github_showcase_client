package com.showcase.retrofit

import com.showcase.moshi.MoshiModule
import com.showcase.okhttp.OkHttpModule
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named

@Module(includes = [MoshiModule::class, OkHttpModule::class])
object RetrofitModule {
    @Provides
    @Named("GitHub")
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.github.com/")
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }
}
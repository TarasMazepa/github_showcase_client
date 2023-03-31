package com.showcase.retrofit

import com.showcase.moshi.MoshiModule
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named

@Module(includes = [MoshiModule::class])
object RetrofitModule {
    @Provides
    @Named("GitHub")
    fun provideRetrofit(moshiConverterFactory: MoshiConverterFactory): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.github.com/")
            .addConverterFactory(moshiConverterFactory)
            .build()
    }
}
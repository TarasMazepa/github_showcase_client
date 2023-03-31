package com.showcase.github

import com.showcase.retrofit.RetrofitModule
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module(includes = [RetrofitModule::class])
object GitHubServiceModule {
    @Provides
    fun provideGitHubService(
        @Named("GitHub") retrofit: Retrofit
    ): GitHubService {
        return retrofit.create(GitHubService::class.java);
    }
}
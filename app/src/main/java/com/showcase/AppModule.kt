package com.showcase

import com.showcase.github.GitHubServiceModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [GitHubServiceModule::class])
interface AppModule {
    @ContributesAndroidInjector
    fun contributeMainActivity(): MainActivity
}
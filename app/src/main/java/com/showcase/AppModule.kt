package com.showcase

import android.content.Context
import com.showcase.github.GitHubServiceModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [GitHubServiceModule::class])
interface AppModule {
    @ContributesAndroidInjector
    fun contributeMainActivity(): MainActivity

    @Binds
    fun application(app: App): Context
}
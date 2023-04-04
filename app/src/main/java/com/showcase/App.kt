package com.showcase

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {
    private val appComponent: AppComponent =
        DaggerAppComponent.builder().appModule(AppModule(this)).build()

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return appComponent;
    }
}
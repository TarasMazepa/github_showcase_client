package com.showcase

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {
    @Provides
    @AppContext
    fun providesContext(): Context {
        return app;
    }
}
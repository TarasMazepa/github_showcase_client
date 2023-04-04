package com.showcase

import com.showcase.organizations.OrganizationsModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, OrganizationsModule::class])
abstract class AppComponent : AndroidInjector<App> {
}
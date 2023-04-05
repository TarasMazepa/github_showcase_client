package com.showcase

import com.showcase.github.GitHubServiceModule
import com.showcase.organization.list.OrganizationsModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, OrganizationsModule::class, GitHubServiceModule::class])
abstract class AppComponent : AndroidInjector<App>
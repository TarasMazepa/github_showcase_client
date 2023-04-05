package com.showcase

import com.showcase.github.GitHubServiceModule
import com.showcase.organization.details.OrganizationDetailsModule
import com.showcase.organization.list.OrganizationsListModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, OrganizationsListModule::class, GitHubServiceModule::class, OrganizationDetailsModule::class])
abstract class AppComponent : AndroidInjector<App>
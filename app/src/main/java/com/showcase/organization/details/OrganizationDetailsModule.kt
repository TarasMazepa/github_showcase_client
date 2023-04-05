package com.showcase.organization.details

import com.showcase.database.DatabaseModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [DatabaseModule::class])
abstract class OrganizationDetailsModule {
    @ContributesAndroidInjector
    abstract fun contributeOrganizationDetailsFragment(): OrganizationDetailsFragment
}
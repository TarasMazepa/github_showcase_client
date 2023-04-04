package com.showcase.organizations

import app.cash.sqldelight.paging3.QueryPagingSource
import com.showcase.Database
import com.showcase.database.DatabaseModule
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import kotlinx.coroutines.Dispatchers

@Module(includes = [DatabaseModule::class])
abstract class OrganizationsModule {
    companion object {
        @Provides
        fun provideOrganizationPagingSource(
            database: Database
        ): OrganizationPagingSource {
            return QueryPagingSource(
                database.organizationQueries.countOrganizations(),
                database.organizationQueries,
                Dispatchers.IO,
                database.organizationQueries::pagingOrganizations
            )
        }
    }

    @ContributesAndroidInjector
    abstract fun contributeOrganizationsListFragment(): OrganizationsListFragment
}
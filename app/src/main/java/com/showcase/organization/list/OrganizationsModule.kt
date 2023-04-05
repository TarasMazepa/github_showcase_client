package com.showcase.organization.list

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
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
        @OptIn(ExperimentalPagingApi::class)
        @Provides
        fun provideOrganizationPager(
            database: Database,
            organizationsRemoteMediator: OrganizationsRemoteMediator
        ): OrganizationPager {
            return Pager(PagingConfig(10), remoteMediator = organizationsRemoteMediator) {
                QueryPagingSource(
                    database.organizationQueries.countOrganizations(),
                    database.organizationQueries,
                    Dispatchers.IO,
                    database.organizationQueries::pagingOrganizations
                )
            }
        }
    }

    @ContributesAndroidInjector
    abstract fun contributeOrganizationsListFragment(): OrganizationsListFragment
}
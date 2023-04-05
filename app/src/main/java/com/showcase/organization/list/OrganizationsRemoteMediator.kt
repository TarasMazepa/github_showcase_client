package com.showcase.organization.list

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.showcase.Database
import com.showcase.github.GitHubService
import com.showcase.organization.Organization
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class OrganizationsRemoteMediator @Inject constructor(
    private val gitHubService: GitHubService, private val database: Database
) : RemoteMediator<Int, Organization>() {
    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, Organization>
    ): MediatorResult {
        Log.d("zxc", "$loadType $state")
        fun MediatorResult.log(): MediatorResult {
            when (this) {
                is MediatorResult.Success -> Log.d("zxc", "success $endOfPaginationReached")
                is MediatorResult.Error -> Log.e("zxc", "error ", throwable)
            }
            return this
        }

        val loadKey = when (loadType) {
            LoadType.REFRESH -> {
                database.organizationQueries.deleteAll()
                null
            }
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true).log()
            LoadType.APPEND -> {
                database.organizationQueries.nextIdToPageFrom().executeAsOne().MAX
                    ?: return MediatorResult.Success(
                        endOfPaginationReached = true
                    ).log()
            }
        }
        val response = gitHubService.listOrganizations(since = loadKey)
        if (!response.isSuccessful) {
            return MediatorResult.Error(
                throwable = RuntimeException(
                    response.errorBody().toString()
                )
            ).log()
        }
        val body = response.body()
        body?.let {
            database.transaction {
                it.forEach {
                    database.organizationQueries.insert(
                        it.id,
                        it.login,
                        it.node_id,
                        it.url,
                        it.repos_url,
                        it.events_url,
                        it.hooks_url,
                        it.issues_url,
                        it.members_url,
                        it.public_members_url,
                        it.avatar_url,
                        it.description
                    )
                }
            }
        }
        return MediatorResult.Success(
            endOfPaginationReached = body?.isEmpty() ?: true
        ).log()
    }
}
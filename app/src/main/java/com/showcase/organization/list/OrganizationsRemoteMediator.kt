package com.showcase.organization.list

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
    private val gitHubService: GitHubService,
    private val database: Database
) :
    RemoteMediator<Int, Organization>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Organization>
    ): MediatorResult {
        val loadKey = when (loadType) {
            LoadType.REFRESH -> null
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull()
                    ?: return MediatorResult.Success(endOfPaginationReached = true)
                lastItem.id
            }
        }
        val response = gitHubService.listOrganizations(since = loadKey)
        if (!response.isSuccessful) {
            return MediatorResult.Error(
                throwable = RuntimeException(
                    response.errorBody().toString()
                )
            )
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
            endOfPaginationReached =
            body?.isNotEmpty() ?: true
        )
    }
}
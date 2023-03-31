package com.showcase.organizations

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.showcase.github.GitHubService
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class OrganizationsRemoteMediator @Inject constructor(private val gitHubService: GitHubService) :
    RemoteMediator<Int, OrganizationsApiModel>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, OrganizationsApiModel>
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
        return MediatorResult.Success(
            endOfPaginationReached =
            response.body()?.isNotEmpty() ?: true
        )
    }
}
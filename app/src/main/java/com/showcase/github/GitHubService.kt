package com.showcase.github

import com.showcase.organizations.OrganizationsApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubService {
    @GET("organizations")
    suspend fun listOrganizations(
        @Query("since") since: Long? = null,
        @Query("per_page") per_page: Int? = null
    ): Response<List<OrganizationsApiModel>>
}
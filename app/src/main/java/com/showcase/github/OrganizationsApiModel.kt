package com.showcase.github

data class OrganizationsApiModel(
    val login: String,
    val id: Long,
    val node_id: String,
    val url: String,
    val repos_url: String,
    val events_url: String,
    val hooks_url: String,
    val issues_url: String,
    val members_url: String,
    val public_members_url: String,
    val avatar_url: String,
    val description: String?,
)
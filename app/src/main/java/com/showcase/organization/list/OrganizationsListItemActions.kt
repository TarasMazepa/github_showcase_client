package com.showcase.organization.list

interface OrganizationsListItemActions {
    fun onItemClicked(id: Long)

    fun onLinkClicked(url: String)
}
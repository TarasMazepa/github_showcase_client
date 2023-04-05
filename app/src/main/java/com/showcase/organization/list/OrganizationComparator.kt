package com.showcase.organization.list

import androidx.recyclerview.widget.DiffUtil
import com.showcase.organization.Organization
import javax.inject.Inject

class OrganizationComparator @Inject constructor() : DiffUtil.ItemCallback<Organization>() {
    override fun areItemsTheSame(oldItem: Organization, newItem: Organization): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Organization, newItem: Organization): Boolean {
        return oldItem == newItem
    }
}
package com.showcase.organization.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.showcase.databinding.OrganizationListItemBinding
import com.showcase.organization.Organization
import javax.inject.Inject

class OrganizationsAdapter @Inject constructor(organizationsComparator: OrganizationComparator) :
    PagingDataAdapter<Organization, OrganizationsAdapter.ViewHolder>(organizationsComparator) {

    inner class ViewHolder(val binding: OrganizationListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.textHeadline.text = item?.login ?: ""
        holder.binding.textSubtitle.text = item?.description ?: ""
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            OrganizationListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
}
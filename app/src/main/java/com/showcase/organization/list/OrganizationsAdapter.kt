package com.showcase.organization.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.showcase.R
import com.showcase.databinding.OrganizationListItemBinding
import com.showcase.organization.Organization
import com.squareup.picasso.Picasso
import javax.inject.Inject

class OrganizationsAdapter @Inject constructor(
    organizationsComparator: OrganizationComparator,
) :
    PagingDataAdapter<Organization, OrganizationsAdapter.ViewHolder>(organizationsComparator) {

    lateinit var organizationsListItemActions: OrganizationsListItemActions

    inner class ViewHolder(val binding: OrganizationListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            Picasso.get().load(item?.avatar_url)
                .error(android.R.drawable.stat_notify_error).into(avatar)
            textHeadline.text = item?.login ?: ""
            val description = item?.description ?: ""
            browserLink.visibility = if (description.isBlank()) View.GONE else View.VISIBLE
            textSubtitle.text = item?.description ?: ""
            root.tag = item?.id
            val url = "https://github.com/${item?.login}"
            browserLink.text = url
            browserLink.tag = url
            browserLink.visibility = if (item == null) View.GONE else View.VISIBLE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            OrganizationListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        with(binding) {
            root.setOnClickListener {
                val tag = it.tag ?: return@setOnClickListener
                organizationsListItemActions.onItemClicked(tag as Long)
            }
            browserLink.setOnClickListener {
                val tag = it.tag ?: return@setOnClickListener
                organizationsListItemActions.onLinkClicked(tag as String)
            }
        }
        return ViewHolder(binding)
    }
}
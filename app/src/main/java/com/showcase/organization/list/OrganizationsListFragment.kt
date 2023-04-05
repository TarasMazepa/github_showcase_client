package com.showcase.organization.list

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.paging.cachedIn
import androidx.recyclerview.widget.LinearLayoutManager
import com.showcase.R
import com.showcase.databinding.OrganizationsListFragmentBinding
import com.showcase.github.GitHubService
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class OrganizationsListFragment : DaggerFragment(), OrganizationsListItemActions {

    private var _binding: OrganizationsListFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<OrganizationsViewModel>()

    @Inject
    lateinit var organizationPager: OrganizationPager

    @Inject
    lateinit var organizationsAdapter: OrganizationsAdapter

    @Inject
    lateinit var gitHubService: GitHubService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = OrganizationsListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val flow = organizationPager.flow.cachedIn(viewModel.viewModelScope)
        binding.list.layoutManager = LinearLayoutManager(context)
        organizationsAdapter.organizationsListItemActions = this
        binding.list.adapter = organizationsAdapter
        lifecycleScope.launch {
            flow.collectLatest { pagingData ->
                organizationsAdapter.submitData(pagingData)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(id: Long) {
        val navController = findNavController()
//        navController.navigate(R.id.SecondFragment, Bundle().apply {
//            putLong("id", id)
//        })
    }

    override fun onLinkClicked(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }
}
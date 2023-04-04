package com.showcase.organizations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.showcase.databinding.OrganizationsListFragmentBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class OrganizationsListFragment : DaggerFragment() {

    private var _binding: OrganizationsListFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<OrganizationsViewModel>()

    @Inject
    lateinit var organizationPagingSource: OrganizationPagingSource

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = OrganizationsListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
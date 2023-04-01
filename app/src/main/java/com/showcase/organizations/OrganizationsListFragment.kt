package com.showcase.organizations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.showcase.R
import com.showcase.databinding.OrganizationsListFragmentBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class OrganizationsListFragment : Fragment() {

    private var _binding: OrganizationsListFragmentBinding? = null

    private val binding get() = _binding!!

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
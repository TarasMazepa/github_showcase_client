package com.showcase.organization.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.showcase.Database
import com.showcase.databinding.FragmentSecondBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class OrganizationDetailsFragment : DaggerFragment() {

    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var database: Database

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getLong("id")
        if (id == null) {
            findNavController().navigateUp()
            return
        }
        database.organizationQueries.selectById(id)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
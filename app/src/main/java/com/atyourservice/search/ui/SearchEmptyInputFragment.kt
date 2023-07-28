package com.atyourservice.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.atyourservice.utils.GetMock
import com.example.atyourservice.databinding.FragmentSearchEmptyInputBinding

class SearchEmptyInputFragment : Fragment() {

    lateinit var binding: FragmentSearchEmptyInputBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchEmptyInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapterCategory = CategoryAdapter()
        val adapterExperts = TopExpertsAdapter()
        binding.searchCategoriesRecycler.adapter = adapterCategory
        binding.searchCategoriesRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        adapterCategory.setCategory(GetMock.mockItemCategory())
        binding.searchTopRatedExpertsRecycler.adapter = adapterExperts
        binding.searchTopRatedExpertsRecycler.layoutManager = LinearLayoutManager(requireContext())
        adapterExperts.setExperts(GetMock.mockItemExperts())
    }
}
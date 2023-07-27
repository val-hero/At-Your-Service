package com.atyourservice.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.atyourservice.utils.GetMock
import com.example.atyourservice.databinding.FragmentMainStartedBinding

class MainStartedFragment : Fragment() {

    lateinit var binding: FragmentMainStartedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainStartedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapterCategory = CategoryAdapter()
        val adapterExperts = TopExpertsAdapter()
        binding.recyclerCategories.adapter = adapterCategory
        binding.recyclerCategories.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        adapterCategory.setCategory(GetMock.mockItemCategory())
        binding.recyclerTopRatedExperts.adapter = adapterExperts
        binding.recyclerTopRatedExperts.layoutManager = LinearLayoutManager(requireContext())
        adapterExperts.setExperts(GetMock.mockItemExperts())
    }
}
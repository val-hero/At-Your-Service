package com.atyourservice.profile.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.atyourservice.R
import com.example.atyourservice.databinding.FragmentProfileUserBinding
import com.google.android.material.tabs.TabLayoutMediator

class ProfileUserFragment : Fragment() {
    private var _binding: FragmentProfileUserBinding? = null
    private val binding get() = _binding!!

    private lateinit var tabMediator: TabLayoutMediator


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter =
            ProfileUserPagerAdapter(childFragmentManager, lifecycle)

        tabMediator = TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Personal"
                1 -> tab.text = "Expert"
                2 -> tab.text = "Company"
            }
        }
        tabMediator.attach()
    }

    companion object {

        @JvmStatic
        fun newInstance() = ProfileUserFragment()
    }
}
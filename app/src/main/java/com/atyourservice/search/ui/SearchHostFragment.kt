package com.atyourservice.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.atyourservice.R
import com.example.atyourservice.databinding.FragmentSearchHostBinding

class SearchHostFragment : Fragment() {

    lateinit var binding: FragmentSearchHostBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchHostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentFragmentManager.commit {
            add(R.id.search_fragment_container, SearchEmptyInputFragment())
        }
    }
}
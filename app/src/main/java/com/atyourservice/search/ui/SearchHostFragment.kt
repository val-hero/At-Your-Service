package com.atyourservice.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.atyourservice.R
import com.example.atyourservice.databinding.FragmentSearchHostBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchHostFragment : Fragment() {

    private lateinit var binding: FragmentSearchHostBinding
    private val viewModel: SearchHostViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchHostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getInfo()
        viewModel.userData.observe(viewLifecycleOwner) {
            val nameUser = it.firstName + " " + it.lastName
            binding.textUserName.text = nameUser
        }

        childFragmentManager.beginTransaction()
            .add(R.id.fragment_container_view, SearchEmptyInputFragment())
            .commit()



        //Для тестирования
        binding.imageClose.setOnClickListener{
            viewModel.delete()
        }
        binding.imageLocation.setOnClickListener{
            viewModel.getInfo()
        }
    }
}

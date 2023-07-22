package com.atyourservice.auth.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.atyourservice.auth.resetpass.ui.ResetpassDialogFragment
import com.example.atyourservice.databinding.FragmentAuthorizationBinding

class AuthorizationFragment : Fragment() {

    lateinit var binding: FragmentAuthorizationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthorizationBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.forgotPasswordTv.setOnClickListener {
            ResetpassDialogFragment().show(childFragmentManager, TAG_RESETPASS)
        }
    }

    companion object{
        const val TAG_RESETPASS ="ResetpassDialog"
    }
}
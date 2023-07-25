package com.atyourservice.auth.ui.authorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.atyourservice.auth.ui.resetpassword.ResetpassDialogFragment
import com.example.atyourservice.R
import com.example.atyourservice.databinding.FragmentAuthorizationBinding

class AuthorizationFragment : Fragment() {

    private lateinit var binding: FragmentAuthorizationBinding

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

        binding.createAccountTv.setOnClickListener {
            findNavController().navigate(R.id.action_authorizationFragment_to_registrationFragment)
        }
    }

    companion object{
        const val TAG_RESETPASS ="ResetpassDialog"
    }
}
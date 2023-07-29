package com.atyourservice.auth.ui.authorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.atyourservice.auth.ui.AuthFlowScreenState
import com.atyourservice.auth.ui.resetpassword.ResetPassDialogFragment
import com.atyourservice.core.utils.getDetails
import com.atyourservice.core.utils.toUiText
import com.example.atyourservice.R
import com.example.atyourservice.databinding.FragmentAuthBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthorizationFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding
    private val viewModel: AuthorizationViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.authForgotPasswordText.setOnClickListener {
            ResetPassDialogFragment().show(childFragmentManager, ResetPassDialogFragment.TAG)
        }

        binding.authCreateAccountText.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_registrationFragment)
        }

        binding.authSignInButton.setOnClickListener {
            viewModel.signInWithEmailAndPassword(
                binding.authEmailEditText.text.toString(),
                binding.authPasswordEditText.text.toString()
            )
        }

        viewModel.screenState.observe(viewLifecycleOwner) { state ->
            render(state)
        }
    }

    private fun render(state: AuthFlowScreenState) {
        when (state) {
            is AuthFlowScreenState.Loading -> {}
            is AuthFlowScreenState.Error -> {
                Toast.makeText(
                    requireContext(),
                    state.errorType.getDetails().toUiText(requireContext()),
                    Toast.LENGTH_LONG
                ).show()
            }

            is AuthFlowScreenState.Success -> {
                findNavController().navigate(R.id.action_authFragment_to_searchHostFragment)
            }
        }
    }
}
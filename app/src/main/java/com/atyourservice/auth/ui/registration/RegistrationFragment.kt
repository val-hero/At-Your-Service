package com.atyourservice.auth.ui.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.atyourservice.auth.ui.AuthFlowScreenState
import com.atyourservice.core.utils.getDetails
import com.atyourservice.core.utils.toUiText
import com.example.atyourservice.R
import com.example.atyourservice.databinding.FragmentRegistrationBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : Fragment() {
    private lateinit var binding: FragmentRegistrationBinding
    private val viewModel by viewModel<RegistrationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submitButton.setOnClickListener {
            viewModel.signUpWithEmailAndPassword(
                binding.emailEditText.text.toString(),
                binding.passwordEditText.text.toString(),
                binding.firstNameEditText.text.toString(),
                binding.lastNameEditText.text.toString()
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
                //После успешной регистрации вернуть на фрагмент авторизации
                //Убрать авторизацию для входа на фрагменте авторизации

                findNavController().navigate(R.id.authorizationFragment)
            }
        }
    }
}
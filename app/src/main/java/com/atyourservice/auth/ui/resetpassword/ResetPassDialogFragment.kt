package com.atyourservice.auth.ui.resetpassword

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.atyourservice.auth.ui.AuthFlowScreenState
import com.atyourservice.core.utils.getDetails
import com.atyourservice.core.utils.toUiText
import com.example.atyourservice.databinding.FragmentDialogResetpassBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResetPassDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentDialogResetpassBinding
    private val viewModel: ResetPassViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDialogResetpassBinding.inflate(inflater, container, false)
        dialog?.window?.let {
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.requestFeature(Window.FEATURE_NO_TITLE)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSendDialogResetpass.setOnClickListener {
            viewModel.sendNewPassword(binding.editTextDialogResetpass.text.toString())
        }

        viewModel.screenState.observe(viewLifecycleOwner) { state ->
            render(state)
        }
    }

    override fun onResume() {
        super.onResume()

        val window = requireDialog().window
        window!!.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window.setGravity(Gravity.CENTER)
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
                Toast.makeText(
                    requireContext(),
                    "Email send successfully",
                    Toast.LENGTH_SHORT
                ).show()

                this.dismiss()
            }
        }
    }

    companion object {
        const val TAG = "reset_pass_dialog"
    }
}
package com.atyourservice.auth.resetpass.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.example.atyourservice.databinding.FragmentDialogResetpassBinding

class ResetpassDialogFragment: DialogFragment() {

    lateinit var binding: FragmentDialogResetpassBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDialogResetpassBinding.inflate(inflater,container, false)
        if (dialog != null && dialog?.getWindow() != null) {
            dialog?.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog?.getWindow()!!.requestFeature(Window.FEATURE_NO_TITLE)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            //для проверки работы кнопки
            Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(
                    Intent.EXTRA_EMAIL,
                    arrayOf(binding.editText.text.toString())
                )
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                requireContext().startActivity(this)

                requireDialog().cancel()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        val window = requireDialog().window
        window!!.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window.setGravity(Gravity.CENTER)
    }
}
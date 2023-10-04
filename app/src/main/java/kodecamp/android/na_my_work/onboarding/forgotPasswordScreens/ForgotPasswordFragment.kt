package kodecamp.android.na_my_work.onboarding.forgotPasswordScreens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.google.android.material.textfield.TextInputEditText
import kodecamp.android.na_my_work.databinding.FragmentForgotPasswordBinding

class ForgotPasswordFragment : Fragment() {

    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.etEmail.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP && isClickOnDrawableEnd(binding.etEmail, event)) {
                binding.etEmail.text?.clear()
                true
            } else {
                false
            }
        }
    }

    private fun isClickOnDrawableEnd(editText: TextInputEditText, event: MotionEvent): Boolean {
        val drawableEnd = editText.compoundDrawablesRelative[2]
        return event.rawX >= editText.right - drawableEnd.bounds.width()
    }
}
package kodecamp.android.na_my_work.ui.ui.password

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kodecamp.android.na_my_work.R
import kodecamp.android.na_my_work.databinding.FragmentResetPasswordBinding
import kodecamp.android.na_my_work.ui.ui.onboarding.OnboardingViewModel
import kodecamp.android.na_my_work.ui.utils.Object
import kodecamp.android.na_my_work.ui.utils.Resource

class ResetPasswordFragment : Fragment() {

    private var _binding: FragmentResetPasswordBinding? = null
    private val binding get() = _binding!!

        private val viewModel: OnboardingViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResetPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textFieldsSetup()
        clickListener()

        viewModel.updateState.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Error -> {
                    Snackbar.make(
                        binding.root,
                        "Password reset failed",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }

                is Resource.Success -> {
                    findNavController().navigate(R.id.action_resetPasswordFragment_to_completeResetPasswordFragment)
                }
                else -> {}
            }
        }
    }

    private fun textFieldsSetup(){
        val textFields = listOf(binding.etPassword, binding.etConfirmPassword)
        textFields.forEach { textInputEditText ->
            textInputEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?, start: Int, count: Int, after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val hasCapitalLetter = s.toString().any { it.isUpperCase() }
                    val hasNumberOrSpecialCharacter =
                        s.toString().any { it.isDigit() || !it.isLetterOrDigit() }
                    val passwordsMatch =
                        binding.etPassword.text.toString() == binding.etConfirmPassword.text.toString()

                    if (hasCapitalLetter) {
                        binding.pwIndicator.setImageResource(R.drawable.green_circle)
                    } else {
                        binding.pwIndicator.setImageResource(R.drawable.red_circle)
                    }

                    if (hasNumberOrSpecialCharacter) {
                        binding.pwIndicator1.setImageResource(R.drawable.green_circle)
                    } else {
                        binding.pwIndicator1.setImageResource(R.drawable.red_circle)
                    }

                    binding.passwordError.visibility = if (binding.etConfirmPassword.text.toString()
                            .isNotBlank() && !passwordsMatch
                    ) {
                        View.VISIBLE
                    } else {
                        View.INVISIBLE
                    }

                    binding.btnResetPassword.isEnabled =
                        hasCapitalLetter && hasNumberOrSpecialCharacter && s.toString().length >= 8 && passwordsMatch
                }

                override fun afterTextChanged(s: Editable?) {}

            })
        }
    }

    private fun clickListener(){
        binding.btnResetPassword.setOnClickListener {
            val user = Object.user
            user!!.password = binding.etConfirmPassword.text.toString()

            viewModel.updateUserInformation(user)
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
}
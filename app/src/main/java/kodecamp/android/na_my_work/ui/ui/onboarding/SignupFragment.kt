package kodecamp.android.na_my_work.ui.ui.onboarding

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns.EMAIL_ADDRESS
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kodecamp.android.na_my_work.R
import kodecamp.android.na_my_work.databinding.FragmentSignupBinding
import kodecamp.android.na_my_work.ui.model.Notification
import kodecamp.android.na_my_work.ui.model.UserEntity
import kodecamp.android.na_my_work.ui.utils.Object.isNamePatternCorrect
import kodecamp.android.na_my_work.ui.utils.Resource
import java.util.Locale

class SignupFragment : Fragment() {
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!

    private val viewModel: OnboardingViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textFieldsSetup()
        clickListeners()

        viewModel.saveState.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
                }

                is Resource.Error -> {
                    Snackbar.make(binding.root, response.message.toString(), Snackbar.LENGTH_SHORT)
                        .show()
                }

                is Resource.Loading -> {}
            }
        }
    }

    private fun textFieldsSetup() {
        binding.btnCreateAcct.isEnabled = false

        val textFields = listOf(
            binding.etName, binding.etEmail, binding.etPassword
        )

        textFields.forEach { editText ->
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?, start: Int, count: Int, after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val fullName = binding.etName.text.toString()
                    val email = binding.etEmail.text.toString()
                    val password = binding.etPassword.text.toString()

                    val hasCapitalLetter = password.any { it.isUpperCase() }

                    val hasNumberOrSpecialCharacter =
                        password.any { it.isDigit() || !it.isLetterOrDigit() }

                    val namePatternCorrect = isNamePatternCorrect(fullName)

                    if (namePatternCorrect) {
                        binding.nameError.visibility = View.INVISIBLE
                    } else {
                        binding.nameError.visibility = View.VISIBLE
                    }

                    if (hasCapitalLetter) {
                        binding.pwIndicator.setImageResource(R.drawable.green_circle)
                        binding.pwHint.text =
                            getString(R.string.password_must_contain_capital_letters)
                    } else {
                        binding.pwIndicator.setImageResource(R.drawable.red_circle)
                        binding.pwHint.text =
                            getString(R.string.password_must_contain_capital_letters)
                    }

                    if (hasNumberOrSpecialCharacter) {
                        binding.pwIndicator1.setImageResource(R.drawable.green_circle)
                        binding.pwHint2.text =
                            getString(R.string.include_numbers_or_special_characters)
                    } else {
                        binding.pwIndicator1.setImageResource(R.drawable.red_circle)
                        binding.pwHint2.text =
                            getString(R.string.include_numbers_or_special_characters)
                    }

                    binding.btnCreateAcct.isEnabled =
                        namePatternCorrect && EMAIL_ADDRESS.matcher(email)
                            .matches() && hasCapitalLetter && hasNumberOrSpecialCharacter && password.length >= 8
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        }
    }

    private fun clickListeners() {
        binding.btnCreateAcct.setOnClickListener {

            val fullName = binding.etName.text.toString()
            val email = binding.etEmail.text.toString().lowercase(Locale.getDefault())
            val password = binding.etPassword.text.toString()
            val signup = UserEntity(
                userId = 0, fullName, email, password, appNotification = Notification(
                    messages = true, viewedProfile = false, nothing = false
                ), emailNotification = Notification(
                    messages = false, viewedProfile = true, nothing = false
                )
            )

            viewModel.saveUserInformation(signup)
        }

        binding.tvSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }
    }

    private fun isPasswordCriteriaMet(password: String): Boolean {
        val hasCapitalLetter = password.any { it.isUpperCase() }
        val hasNumberOrSpecialCharacter = password.any { it.isDigit() || !it.isLetterOrDigit() }

        return hasCapitalLetter && hasNumberOrSpecialCharacter && password.length >= 8
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
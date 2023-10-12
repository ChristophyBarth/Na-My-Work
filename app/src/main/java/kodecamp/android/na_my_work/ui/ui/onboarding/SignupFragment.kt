package kodecamp.android.na_my_work.ui.ui.onboarding

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import kodecamp.android.na_my_work.R
import kodecamp.android.na_my_work.databinding.FragmentSignupBinding
import kodecamp.android.na_my_work.ui.model.UserEntity

class SignupFragment : Fragment() {
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!

    private val viewModel: OnboardingViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnCreateAcct = binding.btnCreateAcct
        btnCreateAcct.isEnabled = false

        val textFields = listOf(
            binding.etName,
            binding.etEmail,
            binding.etPassword
        )

        textFields.forEach { editText ->
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val fullName = binding.etName.text.toString()
                    val email = binding.etEmail.text.toString()
                    val password = binding.etPassword.text.toString()

                    val hasCapitalLetter = password.any { it.isUpperCase() }

                    val hasNumberOrSpecialCharacter = password.any { it.isDigit() || !it.isLetterOrDigit() }

                    val namePatternCorrect = isNamePatternCorrect(fullName)

                    if (namePatternCorrect) {
                        binding.nameError.visibility = View.INVISIBLE
                    } else {
                        binding.nameError.visibility = View.VISIBLE
                    }

                    if (hasCapitalLetter) {
                        binding.pwIndicator.setImageResource(R.drawable.green_circle)
                        binding.pwHint.text = getString(R.string.password_must_contain_capital_letters)
                    } else {
                        binding.pwIndicator.setImageResource(R.drawable.red_circle)
                        binding.pwHint.text = getString(R.string.password_must_contain_capital_letters)
                    }

                    if (hasNumberOrSpecialCharacter) {
                        binding.pwIndicator1.setImageResource(R.drawable.green_circle)
                        binding.pwHint2.text = getString(R.string.include_numbers_or_special_characters)
                    } else {
                        binding.pwIndicator1.setImageResource(R.drawable.red_circle)
                        binding.pwHint2.text = getString(R.string.include_numbers_or_special_characters)
                    }

                    btnCreateAcct.isEnabled = namePatternCorrect && email.isNotBlank() && hasCapitalLetter && hasNumberOrSpecialCharacter && password.length >= 8
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        }

        binding.btnCreateAcct.setOnClickListener {

                val fullName = binding.etName.text.toString()
                val email = binding.etEmail.text.toString()
                val password = binding.etPassword.text.toString()
                val signup = UserEntity(userId = 0, fullName, email, password)

                viewModel.saveUserInformation(signup)
                findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
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

    private fun isNamePatternCorrect(fullName: String): Boolean {

        return fullName.matches(Regex("^[A-Za-z ]+\$"))
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
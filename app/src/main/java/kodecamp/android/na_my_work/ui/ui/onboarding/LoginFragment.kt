package kodecamp.android.na_my_work.ui.ui.onboarding

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kodecamp.android.na_my_work.R
import kodecamp.android.na_my_work.databinding.FragmentLoginBinding
import kodecamp.android.na_my_work.ui.utils.Object
import kodecamp.android.na_my_work.ui.utils.Object.USER_PREF_NAME
import kodecamp.android.na_my_work.ui.utils.Object.USER_PRIMARY_KEY
import kodecamp.android.na_my_work.ui.utils.Resource
import java.util.Locale

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: OnboardingViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignIn.isEnabled = false

        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                updateSignInButtonState()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                updateSignInButtonState()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.btnSignIn.setOnClickListener {
            val email = binding.etEmail.text.toString().lowercase(Locale.getDefault())
            val password = binding.etPassword.text.toString()
            viewModel.login(email, password)
        }

        viewModel.loginState.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    Object.user = response.data
                    Object.primaryKey = response.data!!.userId

                   requireContext().getSharedPreferences(USER_PREF_NAME, Context.MODE_PRIVATE).edit().run {
                        putInt(USER_PRIMARY_KEY, response.data.userId)
                        apply()
                    }

                    findNavController().navigate(R.id.action_loginFragment_to_homeActivity)
                    activity?.finish()
                }
                is Resource.Error -> {
                    val errorMessage = response.message ?: "Login failed."
                    showSnackbar(errorMessage)
                }
                is Resource.Loading -> {
                }
            }
        }

        binding.tvForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }

        binding.tvCreateAccount.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }
    }
    private fun updateSignInButtonState() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        binding.btnSignIn.isEnabled = email.isNotBlank() && password.isNotBlank()
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
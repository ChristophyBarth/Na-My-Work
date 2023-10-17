package kodecamp.android.na_my_work.ui.ui.onboarding.onboardingScreens

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kodecamp.android.na_my_work.R
import kodecamp.android.na_my_work.databinding.FragmentSplashScreenBinding
import kodecamp.android.na_my_work.ui.ui.onboarding.OnboardingViewModel
import kodecamp.android.na_my_work.ui.utils.Object
import kodecamp.android.na_my_work.ui.utils.Object.COMPLETED_ONBOARDING_KEY
import kodecamp.android.na_my_work.ui.utils.Object.USER_PRIMARY_KEY
import kodecamp.android.na_my_work.ui.utils.Resource

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : Fragment() {
    private lateinit var handler: Handler
    private lateinit var nextFragmentRunnable: Runnable

    private var _binding: FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!

    private val viewModel: OnboardingViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handler = Handler(Looper.getMainLooper())

        val sharedPref =
            requireContext().getSharedPreferences(Object.USER_PREF_NAME, Context.MODE_PRIVATE)
        val completedOnboarding = sharedPref.getBoolean(COMPLETED_ONBOARDING_KEY, false)
        val userPrimaryKey = sharedPref.getInt(USER_PRIMARY_KEY, -1)

        if (completedOnboarding && userPrimaryKey != -1) {
            Object.primaryKey = userPrimaryKey
            viewModel.retrieveUserById(userPrimaryKey)

        } else if (completedOnboarding) {
            nextFragmentRunnable = Runnable {
                findNavController().navigate(R.id.action_splashScreenFragment_to_onboardFragment)
            }
            handler.postDelayed(nextFragmentRunnable, 2000)
        } else {
            nextFragmentRunnable = Runnable {
                findNavController().navigate(R.id.action_splashScreenFragment_to_firstFragment)
            }
            handler.postDelayed(nextFragmentRunnable, 2000)
        }

        viewModel.retrieveState.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    val user = response.data
                    Object.user = user
                    Object.primaryKey = user!!.userId

                    nextFragmentRunnable = Runnable {
                        findNavController().navigate(R.id.action_splashScreenFragment_to_homeActivity)
                        activity?.finish()
                    }
                    handler.postDelayed(nextFragmentRunnable, 2000)
                }

                is Resource.Error -> {
                    val errorMessage = response.message ?: "Login failed."
                    showSnackbar(errorMessage)
                }

                is Resource.Loading -> {}
            }
        }
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacks(nextFragmentRunnable)
    }
}
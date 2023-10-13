package kodecamp.android.na_my_work.ui.ui.onboarding.onboardingScreens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kodecamp.android.na_my_work.R
import kodecamp.android.na_my_work.databinding.FragmentThirdBinding
import kodecamp.android.na_my_work.ui.utils.Object

class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGetStarted.setOnClickListener {
            requireContext().getSharedPreferences(Object.USER_PREF_NAME, Context.MODE_PRIVATE).edit().run {
                putBoolean(Object.COMPLETED_ONBOARDING_KEY, true)
                apply()
            }

            findNavController().navigate(R.id.action_thirdFragment_to_onboardFragment)
        }
    }
}
package kodecamp.android.na_my_work.ui.fragments.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kodecamp.android.na_my_work.R
import kodecamp.android.na_my_work.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            notificationSettings.setOnClickListener {
                findNavController().navigate(R.id.action_settingsFragment_to_notificationsSettingsFragment)
            }
            currency.setOnClickListener {
                findNavController().navigate(R.id.action_settingsFragment_to_currencyFragment)
            }
            security.setOnClickListener {
                findNavController().navigate(R.id.action_settingsFragment_to_twoStepFragment)
            }
            reportAProblem.setOnClickListener {
                findNavController().navigate(R.id.action_settingsFragment_to_reportProblemFragment2)
            }
        }
    }
}
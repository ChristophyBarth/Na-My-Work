package kodecamp.android.na_my_work.ui.ui.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kodecamp.android.na_my_work.R
import kodecamp.android.na_my_work.databinding.FragmentNotificationsSettingsBinding

class NotificationsSettingsFragment : Fragment() {
    private var _binding: FragmentNotificationsSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        checkChangedListeners()
    }

    private fun init() {
//        val user = Object.user
        //Get and set CheckBoxes to Notification Preference
    }

    private fun checkChangedListeners() {
        listOf(binding.checkboxMessage, binding.checkboxViewedProfile).forEach { checkBox ->
            checkBox.setOnCheckedChangeListener { _, checked ->
                if (checked) {
                    binding.checkboxNothing.isChecked = false
                }
            }
        }

        binding.checkboxNothing.setOnCheckedChangeListener { _, checked ->
            if (checked) {
                binding.checkboxMessage.isChecked = false
                binding.checkboxViewedProfile.isChecked = false
            }
        }

        listOf(binding.checkboxMessage2, binding.checkboxViewedProfile2).forEach { checkBox ->
            checkBox.setOnCheckedChangeListener { _, checked ->
                if (checked) {
                    binding.checkboxNothing2.isChecked = false
                }
            }
        }

        binding.checkboxNothing2.setOnCheckedChangeListener { _, checked ->
            if (checked) {
                binding.checkboxMessage2.isChecked = false
                binding.checkboxViewedProfile2.isChecked = false
            }
        }

        binding.updateNotificationRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioButtonYes -> {

                }

                R.id.radioButtonNo -> {

                }
            }
        }
    }
}
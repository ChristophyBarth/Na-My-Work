package kodecamp.android.na_my_work.ui.ui.notification

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kodecamp.android.na_my_work.R
import kodecamp.android.na_my_work.databinding.FragmentNotificationsSettingsBinding
import kodecamp.android.na_my_work.ui.ui.home.HomeViewModel
import kodecamp.android.na_my_work.ui.utils.Object
import kodecamp.android.na_my_work.ui.utils.Object.SETTINGS_NEW_UPDATE_KEY
import kodecamp.android.na_my_work.ui.utils.Object.USER_PREF_NAME
import kodecamp.android.na_my_work.ui.utils.Resource

class NotificationsSettingsFragment : Fragment() {
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor
    private lateinit var sharedPreferences: SharedPreferences
    private var _binding: FragmentNotificationsSettingsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by activityViewModels()

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

        viewModel.updateState.observe(viewLifecycleOwner) { content ->
            content.getContentIfNotHandled().let { response ->
                when (response) {
                    is Resource.Error -> {
                        Snackbar.make(
                            binding.root,
                            getString(R.string.sorry_we_could_not_save_your_settings_at_this_time),
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }

                    else -> {}
                }
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    saveUserNotificationSettings()
                    findNavController().navigateUp()
                    isEnabled = false
                }
            })
    }

    private fun saveUserNotificationSettings() {
        binding.apply {
            Object.user!!.apply {
                appNotification?.messages = checkboxMessage.isChecked
                appNotification?.viewedProfile = checkboxViewedProfile.isChecked
                appNotification?.nothing = checkboxNothing.isChecked

                emailNotification?.messages = checkboxMessage2.isChecked
                emailNotification?.viewedProfile = checkboxViewedProfile2.isChecked
                emailNotification?.nothing = checkboxNothing2.isChecked
            }
        }

        viewModel.updateUserInformation(Object.user!!)
    }

    private fun init() {
        val user = Object.user
        sharedPreferences =
            requireContext().getSharedPreferences(USER_PREF_NAME, Context.MODE_PRIVATE)
        sharedPreferencesEditor = sharedPreferences.edit()

        binding.apply {
            checkboxMessage.isChecked = user!!.appNotification!!.messages
            checkboxViewedProfile.isChecked = user.appNotification!!.viewedProfile
            checkboxNothing.isChecked = user.appNotification!!.nothing

            checkboxMessage2.isChecked = user.emailNotification!!.messages
            checkboxViewedProfile2.isChecked = user.emailNotification!!.viewedProfile
            checkboxNothing2.isChecked = user.emailNotification!!.nothing

            if (sharedPreferences.getBoolean(SETTINGS_NEW_UPDATE_KEY, true)) {
                radioButtonYes.isChecked = true
            } else {
                radioButtonNo.isChecked = true
            }
        }

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
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
                    sharedPreferencesEditor.apply {
                        putBoolean(SETTINGS_NEW_UPDATE_KEY, true)
                        apply()
                    }
                }

                R.id.radioButtonNo -> {
                    sharedPreferencesEditor.apply {
                        putBoolean(SETTINGS_NEW_UPDATE_KEY, false)
                        apply()
                    }
                }
            }
        }
    }

}
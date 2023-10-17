package kodecamp.android.na_my_work.ui.ui.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kodecamp.android.na_my_work.R
import kodecamp.android.na_my_work.databinding.FragmentProfileBinding
import kodecamp.android.na_my_work.ui.utils.Object


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            profile.setOnClickListener {
                findNavController().navigate(R.id.action_profileFragment2_to_userProfileFragment)
            }
            settings.setOnClickListener {
                findNavController().navigate(R.id.action_profileFragment2_to_settingsFragment)
            }
            logout.setOnClickListener {
                requireContext().getSharedPreferences(Object.USER_PREF_NAME, Context.MODE_PRIVATE)
                    .edit().run {
                        remove(Object.USER_PRIMARY_KEY)
                        apply()

                        Object.primaryKey = null
                        Object.user = null
                        findNavController().navigate(R.id.action_profileFragment2_to_loginActivity)
                        activity?.finish()
                    }
            }
        }
    }
}
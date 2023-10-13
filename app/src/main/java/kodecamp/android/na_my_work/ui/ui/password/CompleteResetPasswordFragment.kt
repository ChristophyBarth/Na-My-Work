package kodecamp.android.na_my_work.ui.ui.password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kodecamp.android.na_my_work.R
import kodecamp.android.na_my_work.databinding.FragmentCompleteResetPasswordBinding

class CompleteResetPasswordFragment : Fragment() {

    private var _binding: FragmentCompleteResetPasswordBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCompleteResetPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_completeResetPasswordFragment_to_loginFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }
}
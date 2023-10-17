package kodecamp.android.na_my_work.ui.ui.two_step_verification

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kodecamp.android.na_my_work.R
import kodecamp.android.na_my_work.databinding.FragmentTwoStepBinding
import kodecamp.android.na_my_work.ui.ui.home.HomeViewModel
import kodecamp.android.na_my_work.ui.utils.Object
import kodecamp.android.na_my_work.ui.utils.Resource


class TwoStepFragment : Fragment() {
    private var _binding: FragmentTwoStepBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by activityViewModels()

    private var twoStepPin = 0
    private var twoStepPinConfirm = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTwoStepBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (Object.user!!.twoStepVerification) {
            binding.extraSecurityTv.text =
                getString(R.string.two_step_verification_has_been_turned_on)
            binding.turnOnButton.text = getString(R.string.done)
            binding.turnOnButton.setOnClickListener {
                findNavController().navigateUp()
            }
        } else {
            beginTwoStepVerification()
        }

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.updateState.observe(viewLifecycleOwner) { content ->
            content.getContentIfNotHandled().let { response ->
                when (response){
                    is Resource.Error -> {
                        Snackbar.make(
                            binding.root,
                            "Sorry, two-step verification failed",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }

                    is Resource.Success -> {
                        binding.apply {
                            turnOnButton.setOnClickListener {
                                findNavController().navigateUp()
                            }
                            turnOnButton.text = getString(R.string.done)
                            extraSecurityTv.text =
                                getString(R.string.two_step_verification_has_been_turned_on)
                            viewSwitcher.displayedChild = 0

                            Object.user!!.twoStepVerification = true
                        }
                    }

                    else -> {}
                }
            }
        }
    }

    private fun beginTwoStepVerification() {
        binding.apply {
            otpConfirm.message.text = getString(R.string.confirm_your_pin)

            turnOnButton.setOnClickListener {
                when (viewSwitcher.currentView.id) {
                    R.id.extra_security_tv -> {
                        turnOnButton.text = getString(R.string.next)
                        turnOnButton.isEnabled = false
                        otpTextChangeListener()
                        viewSwitcher.showNext()
                    }

                    R.id.otp -> {
                        twoStepPin = otp.otpView.text.toString().toInt()
                        turnOnButton.text = getString(R.string.save)
                        turnOnButton.isEnabled = false
                        otpConfirmChangeListener()
                        viewSwitcher.showNext()
                    }

                    R.id.otp_confirm -> {
                        twoStepPinConfirm = otpConfirm.otpView.text.toString().toInt()
                        if (twoStepPin == twoStepPinConfirm) {
                            viewModel.updateUserInformation(Object.user!!)
                        } else {
                            Snackbar.make(root, "PIN doesn't match.", Snackbar.LENGTH_SHORT).show()
                        }

                    }
                }

            }
        }
    }

    private fun otpConfirmChangeListener() {
        binding.otpConfirm.otpView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?, start: Int, count: Int, after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.turnOnButton.isEnabled = s?.length == 6
            }

            override fun afterTextChanged(s: Editable?) {}

        })
    }

    private fun otpTextChangeListener() {
        binding.otp.otpView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?, start: Int, count: Int, after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.turnOnButton.isEnabled = s?.length == 6
            }

            override fun afterTextChanged(s: Editable?) {}

        })
    }
}
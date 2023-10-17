package kodecamp.android.na_my_work.ui.ui.password

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns.EMAIL_ADDRESS
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import kodecamp.android.na_my_work.R
import kodecamp.android.na_my_work.databinding.FragmentForgotPasswordBinding
import kodecamp.android.na_my_work.ui.ui.onboarding.OnboardingViewModel
import kodecamp.android.na_my_work.ui.utils.Object
import kodecamp.android.na_my_work.ui.utils.Resource
import kotlin.random.Random

class ForgotPasswordFragment : Fragment() {
    companion object {
        private const val TAG = "ForgotPasswordFragment"
        private const val EMAIL_NOTIFICATION_ID = 7
    }

    private var otp = -1
    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!

    private val viewModel: OnboardingViewModel by activityViewModels()

    private lateinit var notificationManager: NotificationManager
    private lateinit var channelID: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        channelID = requireContext().packageName
        createEmailNotificationChannel(channelID)

        binding.etEmail.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP && isClickOnDrawableEnd(
                    binding.etEmail, event
                )
            ) {
                binding.etEmail.text?.clear()
                true
            } else {
                false
            }
        }

        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrBlank()) {
                    binding.emailError.visibility = View.INVISIBLE
                } else {
                    if (EMAIL_ADDRESS.matcher(s.toString()).matches()) {
                        binding.emailError.visibility = View.INVISIBLE
                        binding.btnSendCode.isEnabled = true
                    } else {
                        binding.emailError.visibility = View.VISIBLE
                        binding.btnSendCode.isEnabled = false
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.btnSendCode.setOnClickListener {
            viewModel.retrieveUserByEmail(email = binding.etEmail.text.toString())
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.forgotPasswordState.observe(viewLifecycleOwner) { content ->
            content.getContentIfNotHandled().let { response ->
                when (response) {
                    is Resource.Success -> {
                        Object.user = response.data
                        Object.primaryKey = response.data!!.userId

                        showDialog()
                    }

                    is Resource.Error -> {
                        val errorMessage = response.message ?: "No user found with that email."
                        showSnackbar(errorMessage)
                    }

                    is Resource.Loading -> {
                    }

                    else -> Log.d(TAG, "onViewCreated: content already handled")
                }
            }
        }
    }

    private fun showDialog() {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setTitle(getString(R.string.reset_password))
        dialog.setMessage(
            getString(
                R.string.please_check_the_mail_sent_to_and_follow_the_instructions_to_reset_password,
                binding.etEmail.text.toString()
            ))

        val alertDialog = dialog.create()
        alertDialog.setButton(
            DialogInterface.BUTTON_POSITIVE, getString(R.string.got_it)
        ) { _, _ ->
            displayEmailNotification()
            val bundle = Bundle()
            bundle.putInt("otp", otp)
            bundle.putInt("notificationId", EMAIL_NOTIFICATION_ID)
            findNavController().navigate(
                R.id.action_forgotPasswordFragment_to_forgotPasswordOtpFragment, bundle
            )
        }

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
            ?.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        val radius = resources.getDimension(R.dimen.dialog_corner_radius)
        val shapeDrawable = ShapeDrawable(
            RoundRectShape(
                floatArrayOf(
                    radius, radius, radius, radius, radius, radius, radius, radius
                ), null, null
            )
        )
        shapeDrawable.paint.color = Color.WHITE
        alertDialog.window?.setBackgroundDrawable(shapeDrawable)

        alertDialog.show()
    }

    private fun isClickOnDrawableEnd(editText: TextInputEditText, event: MotionEvent): Boolean {
        val drawableEnd = editText.compoundDrawablesRelative[2]
        return event.rawX >= editText.right - drawableEnd.bounds.width()
    }

    private fun createEmailNotificationChannel(id: String) {
        notificationManager =
            requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(id, getString(R.string.email_notification), importance).apply {
                description = "Email OTP"
            }

            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun displayEmailNotification() {
        otp = generateOTP()

        val notification = NotificationCompat.Builder(requireContext(), channelID)
            .setContentTitle("Your Email OTP").setContentText(otp.toString())
            .setSmallIcon(android.R.drawable.ic_dialog_dialer).setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT).build()

        notificationManager.notify(EMAIL_NOTIFICATION_ID, notification)
    }

    private fun generateOTP(): Int {
        return Random.nextInt(1000, 10000)
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}
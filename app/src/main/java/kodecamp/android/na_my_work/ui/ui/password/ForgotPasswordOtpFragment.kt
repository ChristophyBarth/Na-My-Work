package kodecamp.android.na_my_work.ui.ui.password

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kodecamp.android.na_my_work.R
import kodecamp.android.na_my_work.databinding.FragmentForgotPasswordOtpBinding
import kotlin.random.Random

class ForgotPasswordOtpFragment : Fragment() {
    private var notificationId = -1
    private lateinit var notificationManager: NotificationManager
    private lateinit var channelID: String

    private var otp = -1
    private var _binding: FragmentForgotPasswordOtpBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForgotPasswordOtpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        channelID = requireContext().packageName
        createEmailNotificationChannel(channelID)

        requireArguments().apply {
            otp = getInt("otp")
            notificationId = getInt("notificationId")
        }

        binding.otpView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.btnSendCode.isEnabled = s?.length == 4
            }

            override fun afterTextChanged(s: Editable?) {}

        })

        binding.tvResendCode.setOnClickListener {
            displayEmailNotification()
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnSendCode.setOnClickListener {
            if (binding.otpView.text.toString().toInt() == otp) {
                findNavController().navigate(R.id.action_forgotPasswordOtpFragment_to_resetPasswordFragment)
            } else {
                showSnackbar("Invalid OTP")
            }
        }
    }

    private fun createEmailNotificationChannel(id: String) {
        notificationManager =
            requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(id, "Email Notification", importance).apply {
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

        notificationManager.notify(notificationId, notification)
    }

    private fun generateOTP(): Int {
        return Random.nextInt(1000, 10000)
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}
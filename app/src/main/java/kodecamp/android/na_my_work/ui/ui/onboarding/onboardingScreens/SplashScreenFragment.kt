package kodecamp.android.na_my_work.ui.ui.onboarding.onboardingScreens

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kodecamp.android.na_my_work.R
import kodecamp.android.na_my_work.databinding.FragmentSplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : Fragment() {
    private lateinit var handler: Handler
    private lateinit var navigateToFirstFragment: Runnable
    private var _binding: FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handler = Handler(Looper.getMainLooper())
        navigateToFirstFragment = Runnable {
            findNavController().navigate(R.id.action_splashScreenFragment_to_firstFragment)
        }

        handler.postDelayed(navigateToFirstFragment, 2000)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        handler.removeCallbacks(navigateToFirstFragment)
    }
}
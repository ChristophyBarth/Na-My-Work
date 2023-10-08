package kodecamp.android.na_my_work.ui.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import kodecamp.android.na_my_work.R
import kodecamp.android.na_my_work.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.home_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment2, R.id.categoriesFragment2, R.id.profileFragment2 -> if (binding.bottomNavigationView.visibility != View.VISIBLE) {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }

                else -> if (binding.bottomNavigationView.visibility != View.GONE) {
                    binding.bottomNavigationView.visibility = View.GONE
                }
            }
        }
    }
}
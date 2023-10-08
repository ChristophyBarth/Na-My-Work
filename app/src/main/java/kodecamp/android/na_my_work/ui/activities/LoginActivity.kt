package kodecamp.android.na_my_work.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kodecamp.android.na_my_work.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
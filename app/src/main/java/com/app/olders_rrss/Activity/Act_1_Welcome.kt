package com.app.olders_rrss.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.app.olders_rrss.databinding.ActivityAct1WelcomeBinding

class Act_1_Welcome : AppCompatActivity() {

    private lateinit var binding: ActivityAct1WelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding = ActivityAct1WelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnRegistro = binding.btnSignUp

        btnRegistro.setOnClickListener {
            startActivity(Intent(this, Act_2_SignUp::class.java))
        }
    }
}
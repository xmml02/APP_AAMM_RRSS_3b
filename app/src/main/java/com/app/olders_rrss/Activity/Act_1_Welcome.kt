package com.app.olders_rrss.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.olders_rrss.databinding.ActivityAct1WelcomeBinding

class Act_1_Welcome : AppCompatActivity() {
    // Grupo de variables publicas
    companion object {
        const val RESULT_KEY = ""
    }

    private lateinit var binding: ActivityAct1WelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAct1WelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnRegistro = binding.btnSignUp

        btnRegistro.setOnClickListener {
            val intent = Intent(this, Act_2_SignUp::class.java)
            //intent.putExtra("Login", "ver")
            startActivity(intent)
        }
    }
}
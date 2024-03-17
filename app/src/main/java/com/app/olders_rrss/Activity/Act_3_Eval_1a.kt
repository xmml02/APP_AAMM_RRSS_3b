package com.app.olders_rrss.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.app.olders_rrss.databinding.ActivityAct3Eval1aBinding


class Act_3_Eval_1a : AppCompatActivity() {

    private lateinit var binding: ActivityAct3Eval1aBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding = ActivityAct3Eval1aBinding.inflate(layoutInflater)
        setContentView(binding.root)

        InitComponents()
    }

    private fun InitComponents() {

        binding.btnBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
        binding.btnOK.setOnClickListener {

            startActivity(Intent(this@Act_3_Eval_1a, Act_3_Eval_1b::class.java))
        }
    }
}

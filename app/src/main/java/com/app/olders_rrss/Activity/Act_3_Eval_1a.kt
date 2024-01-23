package com.app.olders_rrss.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.olders_rrss.databinding.ActivityAct3Eval1aBinding

class Act_3_Eval_1a : AppCompatActivity() {

    private lateinit var binding: ActivityAct3Eval1aBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAct3Eval1aBinding.inflate(layoutInflater)
        setContentView(binding.root)

        InitComponents()
    }

    private fun InitComponents() {

        // VUELVE
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        // NAVEGA A PANTALLA EVALUACION
        binding.btnOK.setOnClickListener {
            startActivity(
                Intent(this, Act_3_Eval_1b:: class.java)
            )
        }
    }
}
package com.app.olders_rrss.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.olders_rrss.databinding.ActivityAct2SignupBinding

class Act_2_SignUp : AppCompatActivity() {

    private lateinit var binding: ActivityAct2SignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAct2SignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        InitComponents()
        InitListeners()
        InitUI()
    }

    fun InitComponents() {

    }

    private fun InitListeners() {
        // VOLVER
        binding.btnBack.setOnClickListener {
            //val intent = Intent(this, Act_1_Welcome::class.java)
            // intent.putExtra(RESULT_KEY, result)
            onBackPressed()
            //startActivity(intent)
        }

        // SIGUIENTE
        binding.btnOK.setOnClickListener {
            startActivity(
                Intent(this, Act_3_Eval_1a::class.java)
            )
        }

    }

    private fun InitUI() {

    }
}
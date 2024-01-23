package com.app.olders_rrss.Activity

import android.os.Bundle
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.app.olders_rrss.databinding.ActivityAct3Eval1bBinding

class Act_3_Eval_1b : AppCompatActivity() {

    private lateinit var binding: ActivityAct3Eval1bBinding
    private lateinit var btnSeekBarFuente: SeekBar
    private lateinit var txtProgress: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAct3Eval1bBinding.inflate(layoutInflater)

        setContentView(binding.root)

        InitComponents()
        InitUI()
    }

    private fun InitUI() {
        txtProgress.text = "Si lees esto toca el botón Siguiente"
        txtProgress.textSize = 10F
    }

    private fun InitComponents() {

        fun SetSeekBar(seekBar: SeekBar) {

            seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                    txtProgress.textSize = 10F + i.toFloat()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {}
                override fun onStopTrackingTouch(seekBar: SeekBar) {}
            })
        }

        // VUELVE
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        // NAVEGA A PANTALLA EVALUACION
        binding.btnOK.setOnClickListener {
            //startActivity(
            //    Intent(this, :: class.java)            )
        }

        txtProgress = binding.txtProgress

        btnSeekBarFuente = binding.seekBarFuente

        SetSeekBar(btnSeekBarFuente)

    }
}


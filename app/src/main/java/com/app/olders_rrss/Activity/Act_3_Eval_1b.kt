package com.app.olders_rrss.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.app.olders_rrss.Clases.app
import com.app.olders_rrss.Clases.screenActiv
import com.app.olders_rrss.databinding.ActivityAct3Eval1bBinding
import kotlinx.coroutines.launch

class Act_3_Eval_1b : AppCompatActivity() {

    private lateinit var binding: ActivityAct3Eval1bBinding
    private lateinit var btnSeekBarFuente: SeekBar
    private lateinit var txtProgress: TextView

    private var screenActiv: screenActiv? = screenActiv()
    private var fltSizeFuente: Float = 15F
    private var fltSizeFuenteMin: Float = 15F
    private var booFinalizado: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding = ActivityAct3Eval1bBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("PRUEBA", app.usuario.toString())
        screenActiv = app.usuario!!.GetDiagScreen("Act_3_Eval_1b")

        fltSizeFuente = screenActiv!!.listActividades!![0].listResult[0].select!!.toFloat()
        fltSizeFuenteMin = screenActiv!!.listActividades!![0].listResult[0].option!!.toFloat()

        InitComponents()
        InitUI()
    }

    private fun InitUI() {
        txtProgress.text = "Si lees esto toca el botón Siguiente"
        txtProgress.textSize = fltSizeFuente

        btnSeekBarFuente.progress = fltSizeFuente.toInt() - fltSizeFuenteMin.toInt()

        if (app.usuario!!.strInitScreen != "SignUp") binding.btnBack.visibility = View.GONE
    }

    private fun InitComponents() {

        fun SetSeekBar(seekBar: SeekBar) {

            seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {

                override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {

                    fltSizeFuente = i.toFloat() + fltSizeFuenteMin
                    txtProgress.textSize = fltSizeFuente

                    booFinalizado = true

                    screenActiv!!.listActividades!![0].listResult[0].select =
                        fltSizeFuente.toString()

                    app.usuario?.fltSizeFont = fltSizeFuente
                }
                override fun onStartTrackingTouch(seekBar: SeekBar) {}
                override fun onStopTrackingTouch(seekBar: SeekBar) {}
            })
        }

        binding.btnBack.setOnClickListener {
            lifecycleScope.launch {
                if (booFinalizado) app.usuario!!.SetDiagScreen(screenActiv!!, this@Act_3_Eval_1b)
            }
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnOK.setOnClickListener {

            lifecycleScope.launch {
                if (booFinalizado) app.usuario!!.SetDiagScreen(screenActiv!!, this@Act_3_Eval_1b)
            }
            startActivity(Intent(this@Act_3_Eval_1b, Act_3_Eval_1c::class.java))
        }

        txtProgress = binding.txtProgress
        btnSeekBarFuente = binding.seekBarFuente
        SetSeekBar(btnSeekBarFuente)
    }
}


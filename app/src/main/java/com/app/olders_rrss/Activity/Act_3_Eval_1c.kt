package com.app.olders_rrss.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.app.olders_rrss.Clases.app
import com.app.olders_rrss.Clases.screenActiv
import com.app.olders_rrss.R
import com.app.olders_rrss.databinding.ActivityAct3Eval1cBinding
import kotlinx.coroutines.launch

class Act_3_Eval_1c : AppCompatActivity() {

    private lateinit var binding: ActivityAct3Eval1cBinding
    private var screenActiv: screenActiv? = screenActiv()
    private var fltSizeFuente: Float = 15F

    private var strSelectionA: String = ""
    private var strSelectionB: String = ""
    private var booFinalizado: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_3_eval_1c)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding = ActivityAct3Eval1cBinding.inflate(layoutInflater)
        setContentView(binding.root)

        screenActiv = app.usuario?.GetDiagScreen("Act_3_Eval_1c")
        fltSizeFuente = app.usuario?.fltSizeFont!!

        strSelectionA = screenActiv!!.listActividades!![0].listResult[0].select!!
        strSelectionB = screenActiv!!.listActividades!![1].listResult[0].select!!

        InitComponents()
        InitUI()
    }

    private fun InitComponents() {

        binding.rdSelectionA.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                binding.rdSelectA1.id -> strSelectionA = binding.rdSelectA1.text.toString()
                binding.rdSelectA2.id -> strSelectionA = binding.rdSelectA2.text.toString()
                binding.rdSelectA3.id -> strSelectionA = binding.rdSelectA3.text.toString()
                binding.rdSelectA4.id -> strSelectionA = binding.rdSelectA4.text.toString()
            }
            booFinalizado = true
        }

        binding.rdSelectionB.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                binding.rdSelectB1.id -> strSelectionB = binding.rdSelectB1.text.toString()
                binding.rdSelectB2.id -> strSelectionB = binding.rdSelectB2.text.toString()
                binding.rdSelectB3.id -> strSelectionB = binding.rdSelectB3.text.toString()
                binding.rdSelectB4.id -> strSelectionB = binding.rdSelectB4.text.toString()
            }
            booFinalizado = true
        }

        binding.btnBack.setOnClickListener {
            lifecycleScope.launch { if (booFinalizado) SaveScreen() }
            startActivity(Intent(this@Act_3_Eval_1c, Act_3_Eval_1b::class.java))
        }

        binding.btnOK.setOnClickListener {

            lifecycleScope.launch { if (booFinalizado) SaveScreen() }
            startActivity(Intent(this@Act_3_Eval_1c, Act_3_Eval_1d::class.java))
        }
    }

    private fun InitUI() {

        binding.lblInstrucc.textSize = fltSizeFuente

        when (strSelectionA) {
            binding.rdSelectA1.text.toString() -> binding.rdSelectA1.isChecked = true
            binding.rdSelectA2.text.toString() -> binding.rdSelectA2.isChecked = true
            binding.rdSelectA3.text.toString() -> binding.rdSelectA3.isChecked = true
            binding.rdSelectA4.text.toString() -> binding.rdSelectA4.isChecked = true
        }

        when (strSelectionB) {
            binding.rdSelectB1.text.toString() -> binding.rdSelectB1.isChecked = true
            binding.rdSelectB2.text.toString() -> binding.rdSelectB2.isChecked = true
            binding.rdSelectB3.text.toString() -> binding.rdSelectB3.isChecked = true
            binding.rdSelectB4.text.toString() -> binding.rdSelectB4.isChecked = true
        }

        binding.rdSelectA1.textSize = fltSizeFuente
        binding.rdSelectA2.textSize = fltSizeFuente
        binding.rdSelectA3.textSize = fltSizeFuente
        binding.rdSelectA4.textSize = fltSizeFuente
        binding.rdSelectB1.textSize = fltSizeFuente
        binding.rdSelectB2.textSize = fltSizeFuente
        binding.rdSelectB3.textSize = fltSizeFuente
        binding.rdSelectB4.textSize = fltSizeFuente
    }

    private suspend fun SaveScreen() {
        screenActiv!!.listActividades!![0].listResult[0].select = strSelectionA
        screenActiv!!.listActividades!![1].listResult[0].select = strSelectionB
        app.usuario!!.SetDiagScreen(screenActiv!!, this@Act_3_Eval_1c)
    }

}
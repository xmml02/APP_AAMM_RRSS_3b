package com.app.olders_rrss.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.app.olders_rrss.Clases.app
import com.app.olders_rrss.Clases.screenActiv
import com.app.olders_rrss.databinding.ActivityAct3Eval1fBinding
import kotlinx.coroutines.launch

class Act_3_Eval_1f : AppCompatActivity() {

    lateinit var binding: ActivityAct3Eval1fBinding

    private var screenActiv: screenActiv? = screenActiv()
    private var fltSizeFuente: Float = 15F
    private var booFinalizado: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding = ActivityAct3Eval1fBinding.inflate(layoutInflater)
        setContentView(binding.root)

        screenActiv = app.usuario?.GetDiagScreen("Act_3_Eval_1f")
        fltSizeFuente = app.usuario!!.fltSizeFont!!

        InitUI()
        InitComponents()

    }

    private fun InitComponents() {

        fun SeleccionOpciones(): String {

            var strOpciones = ""
            if (binding.cb1.isChecked) strOpciones += binding.cb1.text.toString() + "; "
            if (binding.cb2.isChecked) strOpciones += binding.cb2.text.toString() + "; "
            if (binding.cb3.isChecked) strOpciones += binding.cb3.text.toString() + "; "
            if (binding.cb4.isChecked) strOpciones += binding.cb4.text.toString() + "; "
            if (binding.cb5.isChecked) strOpciones += binding.cb5.text.toString() + "; "
            if (binding.cb6.isChecked) strOpciones += binding.cb6.text.toString() + "; "
            if (binding.cb7.isChecked) strOpciones += binding.cb7.text.toString() + "; "
            return strOpciones
        }

        binding.radioGroup.setOnClickListener() { booFinalizado = true }

        binding.btnBack.setOnClickListener {
            lifecycleScope.launch {
                screenActiv!!.listActividades!![0].listResult[0].select = SeleccionOpciones()
                if (booFinalizado) SaveScreen()
            }
            startActivity(Intent(this@Act_3_Eval_1f, Act_3_Eval_1e::class.java))
        }

        binding.btnOK.setOnClickListener {

            lifecycleScope.launch {
                screenActiv!!.listActividades!![0].listResult[0].select = SeleccionOpciones()
                if (booFinalizado) SaveScreen()
            }
            val ListEvalPend = app.usuario!!.objDiagnostico!!.CalcularEvalPend()

            if (ListEvalPend.isEmpty()) {
                startActivity(Intent(this@Act_3_Eval_1f, Act_4_Init::class.java))
            } else {
                startActivity(Intent(this@Act_3_Eval_1f, Act_3_Eval_1g::class.java))
            }
        }

    }

    private fun InitUI() {

        val listOpciones = screenActiv!!.listActividades!![0].listResult[0].select!!.split("; ")

        for (select in listOpciones) {
            when (select) {
                binding.cb1.text.toString() -> binding.cb1.isChecked = true
                binding.cb2.text.toString() -> binding.cb2.isChecked = true
                binding.cb3.text.toString() -> binding.cb3.isChecked = true
                binding.cb4.text.toString() -> binding.cb4.isChecked = true
                binding.cb5.text.toString() -> binding.cb5.isChecked = true
                binding.cb6.text.toString() -> binding.cb6.isChecked = true
                binding.cb7.text.toString() -> binding.cb7.isChecked = true
            }
        }

        binding.lblInstrucc.textSize = fltSizeFuente
        binding.cb1.textSize = fltSizeFuente
        binding.cb2.textSize = fltSizeFuente
        binding.cb3.textSize = fltSizeFuente
        binding.cb4.textSize = fltSizeFuente
        binding.cb5.textSize = fltSizeFuente
        binding.cb6.textSize = fltSizeFuente
        binding.cb7.textSize = fltSizeFuente
    }

    private suspend fun SaveScreen() {
        app.usuario!!.SetDiagScreen(screenActiv!!, this@Act_3_Eval_1f)
    }

}
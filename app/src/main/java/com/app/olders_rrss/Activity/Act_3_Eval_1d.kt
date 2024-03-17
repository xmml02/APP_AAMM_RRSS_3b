package com.app.olders_rrss.Activity

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.app.olders_rrss.Clases.app
import com.app.olders_rrss.Clases.screenActiv
import com.app.olders_rrss.R
import com.app.olders_rrss.databinding.ActivityAct3Eval1dBinding
import com.app.olders_rrss.databinding.ActivityAct3Eval1dPuBinding
import kotlinx.coroutines.launch

class Act_3_Eval_1d : AppCompatActivity() {

    private lateinit var binding_pu: ActivityAct3Eval1dPuBinding
    private lateinit var binding: ActivityAct3Eval1dBinding

    private var screenActiv: screenActiv? = screenActiv()
    private var fltSizeFuente: Float = 15F
    private var booFinalizado: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding = ActivityAct3Eval1dBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding_pu = ActivityAct3Eval1dPuBinding.inflate(layoutInflater)

        screenActiv = app.usuario?.GetDiagScreen("Act_3_Eval_1d")
        fltSizeFuente = app.usuario!!.fltSizeFont!!

        InitComponents()
        InitUI()
    }

    private fun InitUI() {
        binding.lblSeleccione.textSize = fltSizeFuente
    }

    private fun InitComponents() {

        binding.btnBack.setOnClickListener {
            lifecycleScope.launch {
                if (booFinalizado) SaveScreen()
            }
            startActivity(Intent(this@Act_3_Eval_1d, Act_3_Eval_1c::class.java))
        }
        binding.btnOK.setOnClickListener {
            lifecycleScope.launch {
                if (booFinalizado) SaveScreen()
            }
            startActivity(Intent(this@Act_3_Eval_1d, Act_3_Eval_1e::class.java))
        }

        binding.image1Phone.setOnClickListener {
            ShowPopUpWindow(
                "Red_phone",
                R.mipmap.img_act_3_eval_1_phone_red
            )
            binding.image1Phone.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY)
        }
        binding.image2Dog.setOnClickListener {
            ShowPopUpWindow(
                "Dog brown happy",
                R.mipmap.img_act_3_eval_1d_dog_happy
            )
            binding.image2Dog.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY)
        }
        binding.image3Sky.setOnClickListener {
            ShowPopUpWindow(
                "Sky blue",
                R.mipmap.img_act_3_eval_1d_sky_blue
            )
            binding.image3Sky.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY)
        }
        binding.image4Sunrise.setOnClickListener {
            ShowPopUpWindow(
                "Sunrise Sun yellow",
                R.mipmap.img_act_3_eval_1d_sunrise
            )
            binding.image4Sunrise.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY)
        }
        binding.image5Bilding.setOnClickListener {
            ShowPopUpWindow(
                "Billboard building",
                R.mipmap.img_act_3_eval_1d_cartelera_city
            )
            binding.image5Bilding.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY)
        }
        binding.image6Plant.setOnClickListener {
            ShowPopUpWindow(
                "Hand plant",
                R.mipmap.img_act_3_eval_1d_plant
            )
            binding.image6Plant.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY)
        }
    }

    private fun ShowPopUpWindow(strImagen: String, imagen: Int) {

        var listColours: List<String> = listOf()
        var strColours = ""
        var strDescripcion = ""

        val popupWindow = PopupWindow(
            binding_pu.root,
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT,
            true
        )

        // busca la actividad con el código "strImagen"
        for (activ in screenActiv!!.listActividades!!) {
            if (activ.strCodigo == strImagen) {
                for (res in activ.listResult) {
                    if (res.desc == "Texto_Descripcion") binding_pu.textoDescripcion.setText(res.select)
                    if (res.desc == "Colours") listColours = res.select!!.split("; ")
                }
                break
            }
        }
        binding_pu.textoDescripcion.requestFocus()

        binding_pu.chipMarron.isChecked = false
        binding_pu.chipBlanco.isChecked = false
        binding_pu.chipNegro.isChecked = false
        binding_pu.chipCeleste.isChecked = false
        binding_pu.chipAzul.isChecked = false
        binding_pu.chipAmarillo.isChecked = false
        binding_pu.chipRojo.isChecked = false
        binding_pu.chipNaranja.isChecked = false
        binding_pu.chipVerde.isChecked = false

        // Definir incio de colores
        for (colour in listColours) {
            when (colour) {
                "brown" -> binding_pu.chipMarron.isChecked = true
                "white" -> binding_pu.chipBlanco.isChecked = true
                "black" -> binding_pu.chipNegro.isChecked = true
                "light_blue" -> binding_pu.chipCeleste.isChecked = true
                "blue" -> binding_pu.chipAzul.isChecked = true
                "yellow" -> binding_pu.chipAmarillo.isChecked = true
                "red" -> binding_pu.chipRojo.isChecked = true
                "orange" -> binding_pu.chipNaranja.isChecked = true
                "green" -> binding_pu.chipVerde.isChecked = true
            }
        }

        binding_pu.textoDescripcion.requestFocus()

        binding_pu.imagenSelect.setImageResource(imagen)
        popupWindow.showAtLocation(binding.root, Gravity.CENTER, 0, 0)

        // BOTON ACEPTAR
        binding_pu.btnAceptarSelection.setOnClickListener {
            strColours = ""
            if (binding_pu.chipMarron.isChecked ) strColours += "brown; "
            if (binding_pu.chipBlanco.isChecked) strColours += "white; "
            if (binding_pu.chipNegro.isChecked) strColours += "black; "
            if (binding_pu.chipCeleste.isChecked) strColours += "light_blue; "
            if (binding_pu.chipAzul.isChecked) strColours += "blue; "
            if (binding_pu.chipAmarillo.isChecked) strColours += "yellow; "
            if (binding_pu.chipRojo.isChecked) strColours += "red; "
            if (binding_pu.chipNaranja.isChecked) strColours += "orange; "
            if (binding_pu.chipVerde.isChecked) strColours += "green; "

            strDescripcion = binding_pu.textoDescripcion.text.toString()

            for (activ in screenActiv!!.listActividades!!) {
                if (activ.strCodigo == strImagen) {

                    for (res in activ.listResult) {
                        if (res.desc == "Texto_Descripcion") res.select = strDescripcion
                        if (res.desc == "Colours") res.select = strColours
                    }
                    break
                }
            }
            booFinalizado = true
            popupWindow.dismiss()
        } // SALIDA
    }

    private suspend fun SaveScreen() {
        app.usuario!!.SetDiagScreen(screenActiv!!, this@Act_3_Eval_1d)
    }
}

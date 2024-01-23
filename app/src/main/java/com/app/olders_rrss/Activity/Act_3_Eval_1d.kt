package com.app.olders_rrss.Activity

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import com.app.olders_rrss.R
import com.app.olders_rrss.databinding.ActivityAct3Eval1dBinding
import com.app.olders_rrss.databinding.ActivityAct3Eval1dPuBinding

class Act_3_Eval_1d : AppCompatActivity() {

    private lateinit var binding_pu: ActivityAct3Eval1dPuBinding
    private lateinit var binding: ActivityAct3Eval1dBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAct3Eval1dBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding_pu = ActivityAct3Eval1dPuBinding.inflate(layoutInflater)
        InitComponents()
    }

    private fun InitComponents() {

        binding.btnBack.setOnClickListener { onBackPressed() }

        binding.btnOK.setOnClickListener {
            //startActivity(
            //    Intent(this, :: class.java)            )
        }

        binding.image1Phone.setOnClickListener { ShowPopUpWindow(R.mipmap.img_act_3_eval_1_phone_red) }
        binding.image2Dog.setOnClickListener { ShowPopUpWindow(R.mipmap.img_act_3_eval_1d_dog_happy) }
        binding.image3Sky.setOnClickListener { ShowPopUpWindow(R.mipmap.img_act_3_eval_1d_sky_blue) }
        binding.image4Sunrise.setOnClickListener { ShowPopUpWindow(R.mipmap.img_act_3_eval_1d_sunrise) }
        binding.image5Bilding.setOnClickListener { ShowPopUpWindow(R.mipmap.img_act_3_eval_1d_cartelera_city) }
        binding.image6Plant.setOnClickListener { ShowPopUpWindow(R.mipmap.img_act_3_eval_1d_plant) }

    }


    // check ShowPopUpWindow

    // ShowPopUpWindow -> Corresponde a la ventana emergente que se muestra al hacer click en una imagen


    private fun ShowPopUpWindow(imagen: Int) {

        val popupWindow = PopupWindow(
            binding_pu.root,
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT,
            false
        )

        binding_pu.chipAzul.setOnClickListener {
            Log.i("Check", binding_pu.chipAzul.checkedState.toString())
            // 0 FALSO - 1 VERDADERO
        }

        binding_pu.textoDescripcion.requestFocus()
        binding_pu.textoDescripcion.setOnClickListener {
            Log.i("teclado", binding_pu.textoDescripcion.toString())
        }

        binding_pu.imagenSelect.setImageResource(imagen)
        popupWindow.showAtLocation(binding.root, Gravity.CENTER, 0, 0)

        binding_pu.btnAceptarSelection.setOnClickListener { popupWindow.dismiss() } // SALIDA

    }
}

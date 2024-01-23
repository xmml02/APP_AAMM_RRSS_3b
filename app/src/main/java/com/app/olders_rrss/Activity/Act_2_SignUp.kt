package com.app.olders_rrss.Activity

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.app.am_rrss.Clases.clsUsuario
import com.app.olders_rrss.databinding.ActivityAct2SignupBinding
import com.app.olders_rrss.databinding.ActivityAct2SignupPuDateBinding
import kotlinx.coroutines.launch
import java.util.Calendar

class Act_2_SignUp : AppCompatActivity() {

    private lateinit var binding: ActivityAct2SignupBinding
    private lateinit var binding_pu: ActivityAct2SignupPuDateBinding
    private lateinit var usuario: clsUsuario
    //private lateinit var dbRoom: clsDB_local

    private var strNombre: String? = ""
    private var strApellido: String? = ""
    private var datNacimiento: Calendar = Calendar.getInstance()
    private var strSexo: String? = ""
    private var intFreqTecn: Int = 3
    private var intExperTecn: Int = 3
    private var strNivelEducacion: String? = ""
    private var strTemaInteres: String? = ""
    private var booNacimSelect: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        datNacimiento.set(1950, 5, 15)
        binding = ActivityAct2SignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding_pu = ActivityAct2SignupPuDateBinding.inflate(layoutInflater)

        InitListeners()

    }

    private fun InitListeners() {

        // Nombre -Error
        binding.etNombre.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                if (binding.etNombre.text.toString().isEmpty()) {
                    binding.etNombre.error = "Campo obligatorio"
                }
            }
        }

        // Apellido -Error
        binding.etApellido.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                if (binding.etApellido.text.toString().isEmpty()) {
                    binding.etApellido.error = "Campo obligatorio"
                }
            }
        }

        // Fecha Nacimiento -> PopUp
        binding.dateNacimiento.setOnClickListener {

            val popupWindow = PopupWindow(
                binding_pu.root,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                false
            )

            popupWindow.showAtLocation(binding.root, Gravity.CENTER, 0, 0)
            binding_pu.datePickerNacimientoPopUp.init(
                datNacimiento.get(Calendar.YEAR),
                datNacimiento.get(Calendar.MONTH),
                datNacimiento.get(Calendar.DAY_OF_MONTH)
            ) { _, year, monthOfYear, dayOfMonth ->

                datNacimiento.set(year, monthOfYear, dayOfMonth)
                booNacimSelect = true
                binding.dateNacimiento.setText(
                    "${dayOfMonth.toString().padStart(2, '0')}/${
                        (monthOfYear + 1).toString().padStart(2, '0')
                    }/$year"
                )
            }

            binding_pu.btnOK.setOnClickListener { popupWindow.dismiss() }
            binding.dateNacimiento.error = null
        }

        // Sexo
        binding.rgSexo.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                binding.rgSexMasc.id -> strSexo = "Masculino"
                binding.rgSexFem.id -> strSexo = "Femenino"
            }
            binding.textSexo.error = null
        }

        // Frecuencia de uso de tecnología
        binding.rbFrecTecn.setOnRatingBarChangeListener { _, rating, _ ->
            intFreqTecn = rating.toInt()
        }

        // Experiencia con tecnología
        binding.rbExperTecn.setOnRatingBarChangeListener { _, rating, _ ->
            intExperTecn = rating.toInt()
        }

        // TEMAS DE INTERES
        binding.cbBienestar.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                strTemaInteres += "Bienestar; "
            } else {
                strTemaInteres = strTemaInteres?.replace("Bienestar; ", "")
            }
            binding.textTemaInteres.error = null
        }
        binding.cbGastronomia.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                strTemaInteres += "Gastronomia; "
            } else {
                strTemaInteres = strTemaInteres?.replace("Gastronomia; ", "")
            }
            binding.textTemaInteres.error = null
        }
        binding.cbCultura.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                strTemaInteres += "Cultura; "
            } else {
                strTemaInteres = strTemaInteres?.replace("Cultura; ", "")
            }
            binding.textTemaInteres.error = null
        }
        binding.cbDeportes.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                strTemaInteres += "Deportes; "
            } else {
                strTemaInteres = strTemaInteres?.replace("Deportes; ", "")
            }
            binding.textTemaInteres.error = null
        }
        binding.cbActualidad.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                strTemaInteres += "Actualidad; "
            } else {
                strTemaInteres = strTemaInteres?.replace("Actualidad; ", "")
            }
            binding.textTemaInteres.error = null
        }
        binding.cbHistoria.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                strTemaInteres += "Historia; "
            } else {
                strTemaInteres = strTemaInteres?.replace("Historia; ", "")
            }
            binding.textTemaInteres.error = null
        }
        binding.cbCiencia.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                strTemaInteres += "Ciencia; "
            } else {
                strTemaInteres = strTemaInteres?.replace("Ciencia; ", "")
            }
            binding.textTemaInteres.error = null
        }

        // NIVEL DE EDUCACION
        binding.rbNivelEducacion.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                binding.rbPrimIncomp.id -> strNivelEducacion = "Primaria Incompleta"
                binding.rbPrimComp.id -> strNivelEducacion = "Primaria Completa"
                binding.rbSecIncomp.id -> strNivelEducacion = "Secundaria Incompleta"
                binding.rbSecComp.id -> strNivelEducacion = "Secundaria Completa"
                binding.rbTercIncomp.id -> strNivelEducacion = "Terciaria Incompleta"
                binding.rbTercComp.id -> strNivelEducacion = "Terciaria Completa"
            }
            binding.textNivelEduc.error = null
        }

        // VOLVER
        binding.btnBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }


        // GUARDAR Y SIGUIENTE
        binding.btnOK.setOnClickListener {

            strNombre = binding.etNombre.text.toString().trim()
            strApellido = binding.etApellido.text.toString().trim()

            // verificar si las variables estan completas y si lo estan, pasar a la siguiente actividad
            if (booNacimSelect && strNombre != "" && strApellido != "" && strSexo != "" &&
                strNivelEducacion != "" && strTemaInteres != "") {

                usuario = clsUsuario(
                    strNombre = strNombre!!,
                    strApellido = strApellido!!,
                    datNacimiento = datNacimiento.time,
                    strNivelEduc = strNivelEducacion!!,
                    strTemasInteres = strTemaInteres!!,
                    intExperTecn = intExperTecn,
                    intFrecUso = intFreqTecn,
                    strCorreo = "",
                    strPassword = "",
                    datRegistro = Calendar.getInstance().time,
                    booLocal = true,
                    booNotif = true
                )

                lifecycleScope.launch {
                    usuario.CrearUsuario(context = this@Act_2_SignUp)
                }

                if (usuario.ComprobarCarga()) {
                    Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT)
                        .show()
                    startActivity(Intent(this, Act_3_Eval_1a::class.java))
                } else Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT).show()

            } else {

                if (!booNacimSelect) binding.dateNacimiento.error = "Campo obligatorio"
                if (strNombre == "") binding.etNombre.error = "Campo obligatorio"
                if (strApellido == "") binding.etApellido.error = "Campo obligatorio"
                if (strSexo == "") binding.textSexo.error = "Campo obligatorio"
                if (strNivelEducacion == "") binding.textNivelEduc.error = "Campo obligatorio"
                if (strTemaInteres == "") binding.textTemaInteres.error = "Campo obligatorio"
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


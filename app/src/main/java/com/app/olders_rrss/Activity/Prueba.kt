package com.app.olders_rrss.Activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.app.olders_rrss.TEST_App_DB
import com.app.olders_rrss.TEST_usuario
import com.app.olders_rrss.databinding.ActivityPruebaBinding
import kotlinx.coroutines.launch

class Prueba : AppCompatActivity() {

    private lateinit var binding: ActivityPruebaBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPruebaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Room.databaseBuilder(this, TEST_App_DB::class.java, "DB_App").build()

        binding.BotonInsert.setOnClickListener {

            lifecycleScope.launch {
                db.TEST_usuarioDAO().insertUsu(
                    TEST_usuario(
                        firstName = binding.editTextText.text.toString(),
                        lastName = binding.editTextTextEmailAddress.text.toString()
                    )
                )
            }

            binding.editTextText.text.clear()
            binding.editTextTextEmailAddress.text.clear()

            Toast.makeText(this, "BIEN PAPAAAAA", Toast.LENGTH_LONG).show()
        }

        binding.BotonConsultar.setOnClickListener {

            //lateinit var texto: List<usuario>

            binding.TextoResultado.text = ""

            lifecycleScope.launch {
                var texto = db.TEST_usuarioDAO().GetUsuarios()

                val resultUsuario = db.TEST_usuarioDAO().FindById(2)

                for (item in texto) {
                    binding.TextoResultado.append("${item.id}:  ${item.firstName}  ${item.lastName}")
                }
            }
        }

    }

}
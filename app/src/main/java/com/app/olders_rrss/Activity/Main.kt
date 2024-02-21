package com.app.olders_rrss.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.app.am_rrss.Clases.clsUsuario
import com.app.olders_rrss.DB.clsDB_local
import com.app.olders_rrss.DB.ent_usuario
import com.app.olders_rrss.R
import kotlinx.coroutines.launch
import java.io.Serializable

class Main : AppCompatActivity() {

    var strLastScreen: String? = null
    var usuario: clsUsuario? = clsUsuario()
    lateinit var entUsu: ent_usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_0_main)

        lifecycleScope.launch {

            val dbRoom =
                Room.databaseBuilder(applicationContext, clsDB_local::class.java, "DB").build()

            entUsu = dbRoom.dao_usuario().FindByLastUsu()

            if (entUsu != null) {

                usuario?.CargarClassUsu(entUsu, dbRoom)
                Log.d("PRUEBA", usuario.toString())

                val listEval = usuario!!.GetListEval()

                for (eval in listEval) {
                    when (eval.strCodigo) {
                        "Diag" -> {
                            if (!eval.booFinalizado) {

                                val listDiag = eval.listDiagnostico
                                for (diag in listDiag!!) {
                                    if (!diag.booFinalizado) {
                                        strLastScreen = diag.strCodigo.toString()
                                        break
                                    }
                                }
                            }
                        }

                        "Eva" -> strLastScreen = "Inicio"
                    }
                }
            } else {
                strLastScreen = "SignUp"
            }

            Log.d("PRUEBA", "LastScreen: $strLastScreen")

            when (strLastScreen) {
                "SignUp" -> {
                    val intent = Intent(this@Main, Act_2_SignUp::class.java)
                    intent.putExtra("usuario", usuario as Serializable)
                    startActivity(intent)
                }

                "Act_3_Eval_1b", "Act_3_Evan_1b"  -> {
                    val intent = Intent(this@Main, Act_3_Eval_1b::class.java)
                    intent.putExtra("usuario", usuario as java.io.Serializable)
                    startActivity(intent)
                }

                "Act_3_Eval_1c" -> {
                    val intent = Intent(this@Main, Act_3_Eval_1c::class.java)
                    intent.putExtra("usuario", usuario as java.io.Serializable)
                    startActivity(intent)
                }

                "Act_3_Eval_1d" -> {
                    val intent = Intent(this@Main, Act_3_Eval_1d::class.java)
                    intent.putExtra("usuario", usuario as java.io.Serializable)
                    startActivity(intent)
                }

                "Act_3_Eval_1e" -> {
                    val intent = Intent(this@Main, Act_3_Eval_1e::class.java)
                    intent.putExtra("usuario", usuario as java.io.Serializable)
                    startActivity(intent)
                }

                "Act_3_Eval_1f" -> {
                    val intent = Intent(this@Main, Act_3_Eval_1f::class.java)
                    intent.putExtra("usuario", usuario as java.io.Serializable)
                    startActivity(intent)
                }

                "Inicio" -> {
                    val intent = Intent(this@Main, Act_4_Init::class.java)
                    intent.putExtra("usuario", usuario as java.io.Serializable)
                    startActivity(intent)
                }
            }
        }
    }
}

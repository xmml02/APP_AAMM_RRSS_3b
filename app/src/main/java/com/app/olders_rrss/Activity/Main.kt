package com.app.olders_rrss.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.app.olders_rrss.Clases.app
import com.app.olders_rrss.DB.clsDB_local
import com.app.olders_rrss.DB.ent_usuario
import com.app.olders_rrss.R
import kotlinx.coroutines.launch

class Main : AppCompatActivity() {

    private var strLastScreen: String? = null
    private var entUsu: ent_usuario? = null
    var usuario = app.usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_0_main)

        lifecycleScope.launch {

            val dbRoom =
                Room.databaseBuilder(applicationContext, clsDB_local::class.java, "DB")
                    .fallbackToDestructiveMigration().build()

            entUsu = dbRoom.dao_usuario().FindByLastUsu()

            if (entUsu != null) {

                usuario!!.CargarClassUsu(entUsu!!, dbRoom)

                val listEval = usuario!!.GetListEval()

                for (eval in listEval) {
                    when (eval.strCodigo) {
                        "Diag" -> {
                            if (!eval.booFinalizado) {

                                val listDiag = eval.listDiagnostico
                                for (diag in listDiag!!) {
                                    if (diag.booFinalizado == false) {
                                        strLastScreen = diag.strCodigo.toString()
                                        break
                                    }
                                }
                            }
                        }

                        "Eva" -> strLastScreen = "Inicio"
                    }
                }
            } else strLastScreen = "SignUp"

            usuario?.strInitScreen = strLastScreen

            when (strLastScreen) {
                "SignUp" -> startActivity(Intent(this@Main, Act_1_Welcome::class.java))
                "Act_3_Eval_1b" -> startActivity(Intent(this@Main, Act_3_Eval_1b::class.java))
                "Act_3_Eval_1c" -> startActivity(Intent(this@Main, Act_3_Eval_1c::class.java))
                "Act_3_Eval_1d" -> startActivity(Intent(this@Main, Act_3_Eval_1d::class.java))
                "Act_3_Eval_1e" -> startActivity(Intent(this@Main, Act_3_Eval_1e::class.java))
                "Act_3_Eval_1f" -> startActivity(Intent(this@Main, Act_3_Eval_1f::class.java))
                "Inicio" -> startActivity(Intent(this@Main, Act_4_Init::class.java))
            }
        }
    }
}

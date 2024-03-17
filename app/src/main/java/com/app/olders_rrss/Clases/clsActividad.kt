package com.app.am_rrss.Clases

import android.content.Context
import androidx.room.Room
import com.app.olders_rrss.Clases.enumCategoria
import com.app.olders_rrss.Clases.enumModulo
import com.app.olders_rrss.Clases.enumPlataforma
import com.app.olders_rrss.DB.clsDB_local
import com.app.olders_rrss.DB.ent_actividad
import java.util.Date

class clsActividad(
    private var strCodigo: String? = "",

    private var eModulo: enumModulo? = enumModulo.RW,
    private var eCategoria: enumCategoria? = enumCategoria.Intro_RRSS,
    private var ePlataforma: enumPlataforma? = enumPlataforma.General,

    private var strDenominacion: String? = "",
    private var strTextoUsu: String? = "",

    private var Eval_1: Double? = 0.0,
    private var Eval_2: Double? = 0.0,
    private var Eval_3: Double? = 0.0,
    private var Eval_4: Double? = 0.0,
    private var Eval_5: Double? = 0.0,
    private var Opc_1: Double? = 0.0,
    private var Opc_2: Double? = 0.0,
    private var Opc_3: Double? = 0.0,

    private var datInicio: Date? = Date(),
    private var datModif: Date? = Date(),
    private var douResultado: Double? = 0.0,
    private var douAvance: Double? = 0.0,
    private var booFinalizado: Boolean? = false,
    private var strLogro: String? = "",
    private var booSelect: Boolean? = false,
    private var booSuggest: Boolean? = false

    //private var listTareas: MutableList<screenActiv>? = mutableListOf()
) {
    private var ID: Int = 0
    private var ID_usu: Int = 0


    override fun toString(): String {
        return "//////////////// ACTIVIDAD //////////////////\n" +
                "$ID - $strDenominacion, Codigo: $strCodigo, Inicio: $datInicio, Modif: $datModif, Resultado: $douResultado, Avance: $douAvance, Finalizado: $booFinalizado, Logro: $strLogro\n" +
                //"Tareas: $listTareas\n" +
                "////////////////////////////////////////////////////////////"
    }

    suspend fun CrearActividad(context: Context) {
        val ent_actividad = GetEntAct()
        val dbRoom = Room.databaseBuilder(context, clsDB_local::class.java, "DB").build()
        dbRoom.dao_actividad().insertActividad(ent_actividad)
    }

    fun GetEntAct(): ent_actividad {
        return ent_actividad(
            id = ID,
            strCodigo = strCodigo!!,
            ID_usu = ID_usu,
            strDenominacion = strDenominacion!!,
            strTextoUsu = strTextoUsu!!,
            strLogro = strLogro!!,
            ePlataforma = ePlataforma!!,
            eCategoria = eCategoria!!,
            eModulo = eModulo!!,
            datInicio = datInicio!!,
            datModif = datModif!!,
            douResultado = douResultado!!,
            douAvance = douAvance!!,
            booFinalizado = booFinalizado!!,
            booSelect = booSelect!!,
            booSuggest = booSuggest!!,
            Eval_1 = Eval_1!!,
            Eval_2 = Eval_2!!,
            Eval_3 = Eval_3!!,
            Eval_4 = Eval_4!!,
            Eval_5 = Eval_5!!,
            Opc_1 = Opc_1!!,
            Opc_2 = Opc_2!!,
            Opc_3 = Opc_3!!
        )
    }

    suspend fun GetListActForID(context: Context): List<ent_actividad> {
        val dbRoom = Room.databaseBuilder(context, clsDB_local::class.java, "DB").build()
        return dbRoom.dao_actividad().GetListActForID(ID_usu)
    }

    suspend fun GetLastID(context: Context): Int {
        val dbRoom = Room.databaseBuilder(context, clsDB_local::class.java, "DB").build()
        return dbRoom.dao_actividad().GetLastID()
    }

    suspend fun FindById(context: Context, ID: Int): ent_actividad {
        val dbRoom = Room.databaseBuilder(context, clsDB_local::class.java, "DB").build()
        return dbRoom.dao_actividad().FindById(ID)
    }
}
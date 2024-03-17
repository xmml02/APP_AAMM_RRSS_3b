package com.app.am_rrss.Clases

import android.content.Context
import androidx.room.Room
import com.app.olders_rrss.Clases.screenActiv
import com.app.olders_rrss.DB.clsDB_local
import com.app.olders_rrss.DB.ent_usuario
import java.util.Date

class clsUsuario(
    private var strNombre: String? = "",
    private var strApellido: String? = "",
    private var booLocal: Boolean? = false,
    private var booNotif: Boolean? = false,
    private var datNacimiento: Date? = Date(),
    private var strNivelEduc: String? = "",
    private var strTemasInteres: String? = "",
    private var intExperTecn: Int? = 0,
    private var intFrecUso: Int? = 0,
    private var strCorreo: String? = "",
    private var strPassword: String? = "",
    private var datRegistro: Date? = Date()
) {
    private var booAlfabetizado: Boolean? = false

    private var booAudio: Boolean? = false
    private var intBotonPixel: Int? = 0
    private var booUsuarioApto: Boolean? = false
    private var ID: Int = 0
    private var booOk: Boolean? = false
    var objDiagnostico: clsEvaluacion? = clsEvaluacion()
    private var objActividad: clsEvaluacion? = clsEvaluacion()
    var fltSizeFont: Float? = 15F
    var strInitScreen: String? = ""
    override fun toString(): String {
        return "//////////////// USUARIO //////////////////\n" +
                "$ID - $strApellido, $strNombre, Local: $booLocal, Notif: $booNotif, Alfab: $booAlfabetizado, Audio: $booAudio\n" +
                "Nacimiento: $datNacimiento\n" +
                "Nivel Educ: $strNivelEduc, Exper Tecn: $intExperTecn, Frec Uso: $intFrecUso\n" +
                "Temas Interes: $strTemasInteres\n" +
                "Registro: $datRegistro\n" +
                "Boton Pixel: $intBotonPixel\n" +
                "Size Fuente: $fltSizeFont\n" +
                "Usuario Apto: $booUsuarioApto\n" +
                "Ok: $booOk\n" +
                "DIAGNOSTICO:\n$objDiagnostico" +
                "////////////////////////////////////////////////////////////"
    }

    suspend fun CrearUsuario(context: Context) {

        val ent_usuario = GetEntUsu()

        val dbRoom = Room.databaseBuilder(context, clsDB_local::class.java, "DB").build()

        dbRoom.dao_usuario().UpdateLastUsuToFalse()
        dbRoom.dao_usuario().insertUsu(ent_usuario)

        val lastID = dbRoom.dao_usuario().GetLastID()
        val lastUsu = dbRoom.dao_usuario().FindById(lastID)

        if (lastUsu.strApellido == ent_usuario.strApellido &&
            lastUsu.strNombre == ent_usuario.strNombre &&
            lastUsu.datNacimiento == ent_usuario.datNacimiento &&
            lastUsu.strNivelEduc == ent_usuario.strNivelEduc &&
            lastUsu.strTemasInteres == ent_usuario.strTemasInteres &&
            lastUsu.intExperTecn == ent_usuario.intExperTecn &&
            lastUsu.intFrecUso == ent_usuario.intFrecUso &&
            lastUsu.datRegistro == ent_usuario.datRegistro
        ) {
            booOk = true
            ID = lastID
        }

        objDiagnostico = CrearObjDiagnostico(context)
        dbRoom.close()
    }

    fun ComprobarCarga() = booOk

    fun GetDiagScreen(strScreenAct: String): screenActiv {
        return objDiagnostico?.listDiagnostico!!.find { it.strCodigo == strScreenAct }!!
    }

    suspend fun SetDiagScreen(screenActiv: screenActiv, context: Context) {

        val dbRoom = Room.databaseBuilder(context, clsDB_local::class.java, "DB").build()

        objDiagnostico!!.datModif = Date()
        screenActiv.datModif = Date()
        screenActiv.booFinalizado = true

        val ent_evaluacion = objDiagnostico!!.GetEntEvaluacion()
        val ent_usuario = GetEntUsu()

        dbRoom.dao_usuario().updateUsu(ent_usuario)
        dbRoom.dao_evaluacion().updateEvaluacion(ent_evaluacion)
        dbRoom.close()
    }

    suspend fun CargarClassUsu(entUsu: ent_usuario, dbRoom: clsDB_local) {

        strNombre = entUsu.strNombre.toString()
        strApellido = entUsu.strApellido.toString()
        booLocal = entUsu.booLocal
        booNotif = entUsu.booNotif
        datNacimiento = entUsu.datNacimiento
        strNivelEduc = entUsu.strNivelEduc.toString()
        strTemasInteres = entUsu.strTemasInteres.toString()
        intExperTecn = entUsu.intExperTecn
        intFrecUso = entUsu.intFrecUso
        strCorreo = entUsu.strCorreo.toString()
        strPassword = entUsu.strPassword.toString()
        datRegistro = entUsu.datRegistro
        booAlfabetizado = entUsu.booAlfabetizado
        booAudio = entUsu.booAudio
        intBotonPixel = entUsu.intBotonPixel
        booUsuarioApto = entUsu.booUsuarioApto
        fltSizeFont = entUsu.fltSizeFont
        ID = entUsu.id.toInt()
        booOk = true

        val ListEntEval = dbRoom.dao_evaluacion().GetListEvalForID(ID)

        for (ent_eval in ListEntEval) {
            when (ent_eval.strCodigo) {
                "Diag" -> objDiagnostico = clsEvaluacion.CargarEvaluacion(ent_eval)
                "Eva" -> break
            }
        }
    }

    fun GetListEval(): List<clsEvaluacion> {
        val listEval = mutableListOf<clsEvaluacion>()

        if (objDiagnostico != null) listEval.add(objDiagnostico!!)
        if (objActividad != null) listEval.add(objActividad!!)
        return listEval
    }

    suspend private fun CrearObjDiagnostico(context: Context): clsEvaluacion {

        objDiagnostico =
            clsEvaluacion(ID_usu = ID, strCodigo = "Diag", strDenominacion = "Diagnostico")

        objDiagnostico?.CrearEvaluacion(context)
        return objDiagnostico!!
    }

    private fun GetEntUsu(): ent_usuario {
        return ent_usuario(
            id = ID,
            strNombre = strNombre,
            strApellido = strApellido,
            booLocal = booLocal,
            booNotif = booNotif,
            datNacimiento = datNacimiento,
            strNivelEduc = strNivelEduc,
            strTemasInteres = strTemasInteres,
            intExperTecn = intExperTecn,
            intFrecUso = intFrecUso,
            strCorreo = strCorreo,
            strPassword = strPassword,
            datRegistro = datRegistro,
            booAlfabetizado = booAlfabetizado,
            booAudio = booAudio,
            intBotonPixel = intBotonPixel,
            booUsuarioApto = booUsuarioApto,
            fltSizeFont = fltSizeFont,
            booLastUsu = true
        )
    }

}


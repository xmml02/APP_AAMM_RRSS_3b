package com.app.am_rrss.Clases

import android.content.Context
import androidx.room.Room
import com.app.olders_rrss.DB.clsDB_local
import com.app.olders_rrss.DB.ent_usuario
import java.util.Date


class clsUsuario(
    var strNombre: String,
    var strApellido: String,
    var booLocal: Boolean,
    var booNotif: Boolean,
    var datNacimiento: Date,
    var strNivelEduc: String,
    var strTemasInteres: String,
    var intExperTecn: Int,
    var intFrecUso: Int,
    var strCorreo: String,
    var strPassword: String,
    var datRegistro: Date
) {

    var booAlfabetizado: Boolean = false
    var booAudio: Boolean = false
    var intBotonPixel: Int = 0
    var booUsuarioApto: Boolean = false
    var ID: Int = 0
    private var booOk: Boolean = false


    suspend fun CrearUsuario(context: Context) {

        val ent_usuario = ent_usuario(
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
            booAlfabetizado = booAlfabetizado,
            booAudio = booAudio,
            intBotonPixel = intBotonPixel,
            booUsuarioApto = booUsuarioApto,
            datRegistro = datRegistro
        )

        val dbRoom = Room.databaseBuilder(context, clsDB_local::class.java, "DB").build()

        dbRoom.dao_usuario().insertUsu(ent_usuario)

        val lastID = dbRoom.dao_usuario().GetLastID()
        val lastUsu = dbRoom.dao_usuario().FindById(lastID)


        if (lastUsu.strApellido == ent_usuario.strApellido
            && lastUsu.strNombre == ent_usuario.strNombre
            && lastUsu.datNacimiento == ent_usuario.datNacimiento
            && lastUsu.strNivelEduc == ent_usuario.strNivelEduc
            && lastUsu.strTemasInteres == ent_usuario.strTemasInteres
            && lastUsu.intExperTecn == ent_usuario.intExperTecn
            && lastUsu.intFrecUso == ent_usuario.intFrecUso
            && lastUsu.datRegistro == ent_usuario.datRegistro
        ) {
            booOk = true
            ID = lastID
        }

        dbRoom.close()
    }

    fun ComprobarCarga() = booOk
}
package com.app.am_rrss.Clases

import java.util.Date

class clsUsuario(
    val strNombre: String,
    val strApellido: String,
    val booLocal: Boolean,
    val booNotif: Boolean,
    val datNacimiento: Date,
    val strNivelEduc: String,
    val strTemasInteres: String,
    val intExperTecn: Int,
    val intFrecUso: Int,
    val intEdad: Int,
    val strCorreo: String,
    val strPassword: String
) {

    var booAlfabetizado: Boolean = false
    var booAudio: Boolean = false
    var intBotonPixel: Int = 0
    var booUsuarioApto: Boolean = false
    var datRegistro: Date = Date()
    val ID: Int = 0
}
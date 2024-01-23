package com.app.am_rrss.Clases

import java.util.Date

class clsEvaluacion(
    val strCodigo: String,
    val strDenominacion: String,
    val datInicio: Date,
    var datModif: Date,
    var douResultado: Double,
    var douAvance: Double,
    booFinalizado: Boolean
) {
    lateinit var objActividad: clsActividad
}
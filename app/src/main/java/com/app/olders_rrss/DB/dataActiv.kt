package com.app.olders_rrss.DB

import java.util.Date

data class screenActiv(
    val id: Int = 1,
    val strCodigo: String? = "",
    val strDenominacion: String? = "",
    val datInicio: Date? = Date(),
    var datModif: Date? = Date(),
    var listActividades: List<simpleActiv>? = null,
    var booFinalizado: Boolean
) {
    // IMPRIMIR EL OBJETO
    override fun toString(): String {
        var strListActividades = ""
        if (listActividades != null) {
            for (act in listActividades!!) {
                strListActividades += act.toString()
            }
        }

        return "   ///SCREEN: $id, $strCodigo ($strDenominacion)\n" +
                " data class screenActiv\n" +
                //"Inicio: $datInicio\n" +
                //"Modificacion: $datModif\n" +
                "      Finalizado: $booFinalizado\n" +
                "      TAREAS:\n$strListActividades"
    }
}

data class simpleActiv(
    val id: Int = 1,
    val strCodigo: String? = "",
    val listResult: List<dataActiv>
) {
    // IMPRIMIR EL OBJETO
    override fun toString(): String {
        var strListResult = ""
        for (res in listResult) {
            strListResult += res.toString()
        }
        return "        //$id, $strCodigo\n" +
                "        //ListResult:\n$strListResult"
    }

}

data class dataActiv(
    var desc: String? = "",
    var option: String? = "",
    var result: String? = "",
    var select: String? = ""
) {
    // IMPRIMIR EL OBJETO
    override fun toString(): String {

        return "            -Desc: $desc\n" +
                "            -Option: $option\n" +
                "            -Result: $result\n" +
                "            -Select: $select\n\n"
    }
}

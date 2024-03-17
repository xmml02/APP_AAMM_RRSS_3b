package com.app.olders_rrss.Clases

import android.app.Application
import com.app.am_rrss.Clases.clsUsuario
import java.io.Serializable
import java.util.Date

// Para hacer publica la variable usuario en toda la app
class app : Application() {
    companion object {
        var usuario: clsUsuario? = clsUsuario()
    }
}
enum class enumModulo { RW, RRSS }
enum class enumCategoria {

    Escritura_Basica,
    Lenguaje_RRSS,
    Tipos_Lectura,
    Escritura_Intermedia,
    Accesibilidad_Android,
    Intro_RRSS,
    Interacc_Pasiva,
    Configuracion,
    Interacc_Basica,
    Interacci_Intermedia,
    Seguridad_Privacidad
}
enum class enumPlataforma {
    General,
    Facebook,
    Youtube,
    Whatsapp,
    Instagram,
    TikTok,
    X_Twitter,
    Pinterest,
    Fb_Messenger,
    LinkedIn
}

// Para habilitar la navegacion entre pantallas mediante la KEY
data class ListItem(val key: String, val strActDesc: String)
data class screenActiv(
    val id: Int = 1,
    val strCodigo: String? = "",
    val strDenominacion: String? = "",
    val datInicio: Date? = Date(),
    var datModif: Date? = Date(),
    var listActividades: List<simpleActiv>? = null,
    var booFinalizado: Boolean? = false
) : Serializable {
    override fun toString(): String {
        var strListActividades = ""
        if (listActividades != null) {
            for (act in listActividades!!) {
                strListActividades += act.toString()
            }
        }

        return "   ///SCREEN: $id, $strCodigo ($strDenominacion)\n" +
                "      Data class screenActiv\n" +
                //"Inicio: $datInicio\n" +
                "      Modificacion: $datModif\n" +
                "      Finalizado: $booFinalizado\n" +
                "      listActividades: List<simpleActiv>:\n$strListActividades"
    }
}
data class simpleActiv(
    val id: Int = 1,
    val strCodigo: String? = "",
    val listResult: List<dataActiv>
) : Serializable {
    override fun toString(): String {
        var strListResult = ""
        for (res in listResult) {
            strListResult += res.toString()
        }
        return "        //Data class simpleActiv\n" +
                "        //$id, $strCodigo\n" +
                "        //ListResult: List<dataActiv>:\n$strListResult"
    }

}
data class dataActiv(
    var desc: String? = "",
    var option: String? = "",
    var result: String? = "",
    var select: String? = ""
) : Serializable {
    override fun toString(): String {

        return "            -Data class dataActiv\n" +
                "            -Desc: $desc\n" +
                "            -Option: $option\n" +
                "            -Result: $result\n" +
                "            -Select: $select\n\n"
    }
}

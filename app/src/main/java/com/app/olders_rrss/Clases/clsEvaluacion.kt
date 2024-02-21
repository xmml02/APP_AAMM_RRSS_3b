package com.app.am_rrss.Clases

import android.content.Context
import androidx.room.Room
import com.app.olders_rrss.DB.clsDB_local
import com.app.olders_rrss.DB.dataActiv
import com.app.olders_rrss.DB.ent_evaluacion
import com.app.olders_rrss.DB.screenActiv
import com.app.olders_rrss.DB.simpleActiv
import java.util.Date

class clsEvaluacion(
    var ID_usu: Int = 0,
    var strCodigo: String = "",
    var strDenominacion: String = "",
    var datInicio: Date = Date()

) {
    lateinit var objActividad: clsActividad
    var ID: Int = 0
    private var booOk: Boolean = false
    var datModif: Date = Date()
    var douResultado: Double = 0.0
    var douAvance: Double = 0.0
    var booFinalizado: Boolean = false
    var listDiagnostico: List<screenActiv>? = null


    override fun toString(): String {

        var strEval = ""
        for (diag in listDiagnostico!!) {
            strEval += "${diag.toString()}\n"
        }


        return "---ID: $ID, $strCodigo ( $strDenominacion )\n" +
                //"   Inicio: $datInicio\n" +
                //"   Modificacion: $datModif\n" +
                "   Resultado: $douResultado, Avance: $douAvance, Finalizado: $booFinalizado\n" +
                "   LISTA:\n$strEval"
    }

    // Para ejecutar a metodo sin instanciar la clase
    companion object {
        fun CargarEvaluacion(ent_evaluacion: ent_evaluacion): clsEvaluacion {
            val clsEvaluacion = clsEvaluacion()
            clsEvaluacion.CrearObjeto(ent_evaluacion)
            return clsEvaluacion
        }
    }

    suspend fun CrearEvaluacion(context: Context) {
        val ent_evaluacion = ent_evaluacion(
            ID_usu = ID_usu,
            strCodigo = strCodigo,
            strDenominacion = strDenominacion,
            datInicio = datInicio,
            datModif = datModif,
            douResultado = douResultado,
            douAvance = douAvance,
            booFinalizado = booFinalizado,
            listDiagnostico = CrearListaDiagnostico()
        )
        val dbRoom = Room.databaseBuilder(context, clsDB_local::class.java, "DB").build()
        dbRoom.dao_evaluacion().insertEvaluacion(ent_evaluacion)

        val lastID = dbRoom.dao_evaluacion().GetLastID()
        val lastEval = dbRoom.dao_evaluacion().FindById(lastID)

        if (lastEval.ID_usu == ent_evaluacion.ID_usu && lastEval.strCodigo == ent_evaluacion.strCodigo && lastEval.datInicio == ent_evaluacion.datInicio && lastEval.datModif == ent_evaluacion.datModif) {
            booOk = true
            ID = lastID
        }
    }

    // Para companion objet
    private fun CrearObjeto(ent_evaluacion: ent_evaluacion): clsEvaluacion {
        ID = ent_evaluacion.id
        ID_usu = ent_evaluacion.ID_usu
        strCodigo = ent_evaluacion.strCodigo
        strDenominacion = ent_evaluacion.strDenominacion
        datInicio = ent_evaluacion.datInicio

        datModif = ent_evaluacion.datModif
        douResultado = ent_evaluacion.douResultado
        douAvance = ent_evaluacion.douAvance
        booFinalizado = ent_evaluacion.booFinalizado
        listDiagnostico = ent_evaluacion.listDiagnostico
        booOk = true

        return this
    }

    fun ComprobarCarga() = booOk

    private fun CrearListaDiagnostico(): List<screenActiv> {
        listDiagnostico = listOf(
            screenActiv(
                id = 1,
                strCodigo = "Act_3_Eval_1b",
                strDenominacion = "Size letra",
                datInicio = Date(),
                datModif = Date(),
                listActividades = listOf(
                    simpleActiv(
                        id = 1, strCodigo = "size_letra", listResult = listOf(
                            dataActiv(
                                "Texto_Descripcion", "10F", "", ""
                            )
                        )
                    )
                ),
                booFinalizado = false
            ), screenActiv(
                id = 2,
                strCodigo = "Act_3_Eval_1c",
                strDenominacion = "Lecto_Escucha",
                datInicio = Date(),
                datModif = Date(),
                listActividades = listOf(
                    simpleActiv(
                        id = 1, strCodigo = "Casa", listResult = listOf(
                            dataActiv(
                                "selecciona_audio", "Monte; Casa; Pintura; Pala;", "Casa", ""
                            )
                        )
                    ),

                    simpleActiv(
                        id = 2, strCodigo = "Redes sociales", listResult = listOf(
                            dataActiv(
                                "seleccione_audio",
                                "Mensaje publico; Redes sociales; Amigos; Compartir;",
                                "Redes sociales",
                                ""
                            )
                        )
                    )

                ),
                booFinalizado = false
            ), screenActiv(
                id = 3,
                strCodigo = "Act_3_Eval_1d",
                strDenominacion = "Lecto_Escritura_Imagen",
                datInicio = Date(),
                datModif = Date(),
                listActividades = listOf(
                    simpleActiv(
                        id = 1, strCodigo = "Red_phone", listResult = listOf(
                            dataActiv(
                                "Texto_Descripcion", "Red phone", "Telefono rojo antiguo", ""
                            ), dataActiv(
                                "Colours",
                                "brown; white; black; light_blue; blue; yellow; red; orange; green;",
                                "red; white; black;",
                                ""
                            )
                        )
                    ), simpleActiv(
                        id = 2, strCodigo = "Dog brown happy", listResult = listOf(
                            dataActiv(
                                "Texto_Descripcion",
                                "Perro feliz con la boca abierta",
                                "Dog brown happy",
                                ""
                            ), dataActiv(
                                "Colours",
                                "brown; white; black; light_blue; blue; yellow; red; orange; green;",
                                "brown; white; black; orange;",
                                ""
                            )
                        )
                    ), simpleActiv(
                        id = 3, strCodigo = "Sky blue", listResult = listOf(
                            dataActiv(
                                "Texto_Descripcion",
                                "Cielo despejado celeste con nubes blancas",
                                "Sky blue",
                                ""
                            ), dataActiv(
                                "Colours",
                                "brown; white; black; light_blue; blue; yellow; red; orange; green;",
                                "light_blue; blue; white",
                                ""
                            )
                        )
                    ), simpleActiv(
                        id = 4, strCodigo = "Sunrise Sun yellow", listResult = listOf(
                            dataActiv(
                                "Texto_Descripcion",
                                "Amanecer con sol amarillo en playa con arena",
                                "Sunrise Sun yellow",
                                ""
                            ), dataActiv(
                                "Colours",
                                "brown; white; black; light_blue; blue; yellow; red; orange; green;",
                                "licht_blue; yellow; white; brown; orange;",
                                ""
                            )
                        )
                    ), simpleActiv(
                        id = 5, strCodigo = "Billboard building", listResult = listOf(
                            dataActiv(
                                "Texto_Descripcion",
                                "Cartelera blanca en edificio antiguo con cielo celeste",
                                "",
                                ""
                            ), dataActiv(
                                "Colours",
                                "brown; white; black; light_blue; blue; yellow; red; orange; green;",
                                "brown; white; light_blue; blue; black;",
                                ""
                            )
                        )
                    ), simpleActiv(
                        id = 6, strCodigo = "Hand plant", listResult = listOf(
                            dataActiv(
                                "Texto_Descripcion",
                                "Mano con planta verde con la naturaleza de fondo",
                                "Hand plant",
                                ""
                            ), dataActiv(
                                "Colours",
                                "brown; white; black; light_blue; blue; yellow; red; orange; green;",
                                "green; white; brown; orange; light_blue;",
                                ""
                            )
                        )
                    )
                ),
                booFinalizado = false
            ), screenActiv(
                id = 4,
                strCodigo = "Act_3_Eval_1e",
                strDenominacion = "Motriz",
                datInicio = Date(),
                datModif = Date(),
                listActividades = listOf(
                    simpleActiv(
                        id = 1, strCodigo = "Blanco", listResult = listOf(
                            dataActiv(
                                "Flecha", "0", "1", ""
                            )
                        )
                    )
                ),
                booFinalizado = false
            ), screenActiv(
                id = 5,
                strCodigo = "Act_3_Eval_1f",
                strDenominacion = "Concepto_RRSS",
                datInicio = Date(),
                datModif = Date(),
                listActividades = listOf(
                    simpleActiv(
                        id = 1, strCodigo = "Concepto RRSS", listResult = listOf(
                            dataActiv(
                                "Texto_Seleccionado",
                                "Plataformas para conectar y comunicarse en línea; " + "Herramientas digitales para compartir información y fotos; " + "Sitios web para comprar y vender productos; " + "Medios de comunicación como la televisión y la radio; " + "Espacios virtuales para encontrar noticias y artículos; " + "Aplicaciones móviles para editar fotos y videos; " + "Plataformas para buscar empleo y hacer contactos profesionales; ",
                                "Plataformas para conectar y comunicarse en línea; " + "Herramientas digitales para compartir información y fotos; " + "Medios de comunicación como la televisión y la radio; " + "Espacios virtuales para encontrar noticias y artículos; " + "Plataformas para buscar empleo y hacer contactos profesionales; ",
                                ""
                            )
                        )
                    )
                ),
                booFinalizado = false
            )

        )

        return listDiagnostico!!
    }


}



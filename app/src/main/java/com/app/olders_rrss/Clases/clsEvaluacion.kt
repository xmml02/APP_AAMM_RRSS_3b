package com.app.am_rrss.Clases

import android.content.Context
import androidx.room.Room
import com.app.olders_rrss.Clases.ListItem
import com.app.olders_rrss.Clases.dataActiv
import com.app.olders_rrss.Clases.screenActiv
import com.app.olders_rrss.Clases.simpleActiv
import com.app.olders_rrss.DB.clsDB_local
import com.app.olders_rrss.DB.ent_evaluacion
import java.util.Date
import kotlin.math.exp

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
    var listDiagnostico: MutableList<screenActiv>? = null
    var listActividades: MutableList<clsActividad>? = null

    companion object {
        // Para ejecutar a metodo sin instanciar la clase
        fun CargarEvaluacion(ent_evaluacion: ent_evaluacion): clsEvaluacion {
            val clsEvaluacion = clsEvaluacion()
            clsEvaluacion.CrearObjeto(ent_evaluacion)
            return clsEvaluacion
        }
    }

    override fun toString(): String {

        var strEval = ""
        for (diag in listDiagnostico!!) {
            strEval += "${diag.toString()}\n"
        }

        return "---ID: $ID, $strCodigo ( $strDenominacion )\n" +
                //"   Inicio: $datInicio\n" +
                "   class clsEvaluacion\n" +
                "   Resultado: $douResultado, Avance: $douAvance, Finalizado: $booFinalizado\n" +
                "   listDiagnostico: MutableList<screenActiv>:\n$strEval"
    }

    suspend fun CrearEvaluacion(context: Context) {

        listDiagnostico = CrearListaDiagnostico()

        val ent_evaluacion = ent_evaluacion(
            ID_usu = ID_usu,
            strCodigo = strCodigo,
            strDenominacion = strDenominacion,
            datInicio = datInicio,
            datModif = datModif,
            douResultado = douResultado,
            douAvance = douAvance,
            booFinalizado = booFinalizado,
            listDiagnostico = listDiagnostico!!
        )
        val dbRoom = Room.databaseBuilder(context, clsDB_local::class.java, "DB").build()

        dbRoom.dao_evaluacion().insertEvaluacion(ent_evaluacion)

        val lastID = dbRoom.dao_evaluacion().GetLastID()
        val lastEval = dbRoom.dao_evaluacion().FindById(lastID)

        if (lastEval.ID_usu == ent_evaluacion.ID_usu &&
            lastEval.strCodigo == ent_evaluacion.strCodigo &&
            lastEval.datInicio == ent_evaluacion.datInicio &&
            lastEval.datModif == ent_evaluacion.datModif
        ) {
            booOk = true
            ID = lastID
        }
        dbRoom.close()
    }

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

    fun CalcularEvalPend(): MutableList<ListItem> {

        val ListFinalizado: MutableList<ListItem> = mutableListOf()
        for (diag in listDiagnostico!!) {

            if (diag.booFinalizado == false) ListFinalizado.add(
                ListItem(
                    diag.strCodigo.toString(),
                    diag.strDenominacion.toString()
                )
            )
        }
        return ListFinalizado
    }

    fun ComprobarCarga() = booOk

    fun GetEntEvaluacion(): ent_evaluacion {
        return ent_evaluacion(
            id = ID,
            ID_usu = ID_usu,
            strCodigo = strCodigo,
            strDenominacion = strDenominacion,
            datInicio = datInicio,
            datModif = datModif,
            douResultado = douResultado,
            douAvance = douAvance,
            booFinalizado = booFinalizado,
            listDiagnostico = listDiagnostico!!
        )
    }

    private fun CrearListaDiagnostico(): MutableList<screenActiv> {

        val listDiagnostico = listOf(
            screenActiv(
                id = 1,
                strCodigo = "Act_3_Eval_1b",
                strDenominacion = "Ajuste Visual: ¿Cúal es el alcance de nuestra visión?",
                datInicio = Date(),
                datModif = Date(),
                listActividades = listOf(
                    simpleActiv(
                        id = 1, strCodigo = "size_letra", listResult = listOf(
                            dataActiv(
                                "Texto_Float", "22", "", "22"
                            )
                        )
                    )
                ),
                booFinalizado = false
            ),
            screenActiv(
                id = 2,
                strCodigo = "Act_3_Eval_1c",
                strDenominacion = "Comprensión Auditiva y Lectura: probemos nuestros oidos",
                datInicio = Date(),
                datModif = Date(),
                listActividades = listOf(
                    simpleActiv(
                        id = 1, strCodigo = "Casa", listResult = listOf(
                            dataActiv(
                                "selecciona_audio", "Monte; Casa; Pintura; Pala; ", "Casa", ""
                            )
                        )
                    ),

                    simpleActiv(
                        id = 2, strCodigo = "Redes sociales", listResult = listOf(
                            dataActiv(
                                "seleccione_audio",
                                "Mensaje publico; Redes sociales; Amigos; Compartir; ",
                                "Redes sociales",
                                ""
                            )
                        )
                    )

                ),
                booFinalizado = false
            ),
            screenActiv(
                id = 3,
                strCodigo = "Act_3_Eval_1d",
                strDenominacion = "Descripción Visual y Escritura: lo que vemos en palabras y colores!",
                datInicio = Date(),
                datModif = Date(),
                listActividades = listOf(
                    simpleActiv(
                        id = 1, strCodigo = "Red_phone", listResult = listOf(
                            dataActiv(
                                "Texto_Descripcion",
                                "Red phone",
                                "Telefono rojo antiguo",
                                ""
                            ), dataActiv(
                                "Colours",
                                "brown; white; black; light_blue; blue; yellow; red; orange; green; ",
                                "red; white; black; ",
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
                                "brown; white; black; light_blue; blue; yellow; red; orange; green; ",
                                "brown; white; black; orange; ",
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
                                "brown; white; black; light_blue; blue; yellow; red; orange; green; ",
                                "light_blue; blue; white; ",
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
                                "brown; white; black; light_blue; blue; yellow; red; orange; green; ",
                                "licht_blue; yellow; white; brown; orange; ",
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
                                "brown; white; black; light_blue; blue; yellow; red; orange; green; ",
                                "brown; white; light_blue; blue; black; ",
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
                                "brown; white; black; light_blue; blue; yellow; red; orange; green; ",
                                "green; white; brown; orange; light_blue; ",
                                ""
                            )
                        )
                    )
                ),
                booFinalizado = false
            ),
            screenActiv(
                id = 4,
                strCodigo = "Act_3_Eval_1e",
                strDenominacion = "Coordinación Motora y Percepción Visual: acertemos al blanco",
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
            ),
            screenActiv(
                id = 5,
                strCodigo = "Act_3_Eval_1f",
                strDenominacion = "Concepto Redes Sociales: pongamos a prueba lo que sabemos!",
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

        return listDiagnostico.toMutableList()
    }

    private fun CalculateOptionScore(correctOptions: String, selectedOptions: String): Double {

        val correctOptionsList = correctOptions.split(";").map { it.trim() }
        val selectedOptionsList = selectedOptions.split(";").map { it.trim() }

        var correctSelected = 0
        var incorrectSelected = 0

        for (opcion in selectedOptionsList) {
            if (opcion in correctOptionsList) {
                correctSelected++
            } else {
                incorrectSelected++
            }
        }

        var opcionesNeto = correctSelected - incorrectSelected
        if (opcionesNeto < 0) opcionesNeto = 0

        return opcionesNeto.toDouble() / correctOptionsList.size
    }

    private fun CalcularWordsScore(userText: String, searchWords: String): Double {

        fun CompararPalabrasExponencial(palabra1: String, palabra2: String): Double {

            fun levenshteinDistance(lhs: CharSequence, rhs: CharSequence): Double {
                val lhsLength = lhs.length
                val rhsLength = rhs.length

                var cost = Array(lhsLength + 1) { it }
                for (i in 1 until rhsLength + 1) {
                    val newCost = Array(lhsLength + 1) { 0 }
                    newCost[0] = i

                    for (j in 1 until lhsLength + 1) {
                        val match = if (lhs[j - 1] == rhs[i - 1]) 0 else 1

                        val costReplace = cost[j - 1] + match
                        val costInsert = cost[j] + 1
                        val costDelete = newCost[j - 1] + 1

                        newCost[j] = minOf(costInsert, costDelete, costReplace)
                    }
                    cost = newCost
                }
                val distanciaLevenshtein = cost[lhsLength]
                val longitudMaxima = maxOf(lhs.length, rhs.length)

                // Calcular la similitud
                return 1.0 - (distanciaLevenshtein.toDouble() / longitudMaxima)
            }

            val distanciaLevenshtein = levenshteinDistance(palabra1, palabra2)

            return if (distanciaLevenshtein >= 0.5) {
                1 - exp(-10 * (distanciaLevenshtein - 0.5))
            } else {
                0.0
                //exp(10 * (distanciaLevenshtein - 0.5))
            }
        }

        val userWords = userText.toLowerCase().split(" ").toMutableList()
        // Se quita signos de puntuacion
        for (i in 0 until userWords.size) {
            userWords[i] = userWords[i].replace(Regex("[^A-Za-z0-9 ]"), "")
        }

        val wordsToSearch = searchWords.toLowerCase().split(" ")

        // val intervalo = 1 / wordsToSearch.size.toDouble()
        var contador = 0
        var score: Double = 0.0
        for (wordToSearch in wordsToSearch) {
            for (userWord in userWords) {
                if (userWord == wordToSearch) {
                    score += 1.0
                    contador++
                    break
                } else {
                    //val distance = compararPalabrasConCurvaExponencial(userWord, wordToSearch)
                    //val maxLength = maxOf(userWord.length, wordToSearch.length)
                    //score += (intervalo - distance / maxLength)
                    val distanciaLevenshtein = CompararPalabrasExponencial(userWord, wordToSearch)

                    score += distanciaLevenshtein
                    if (distanciaLevenshtein >= 0.5) contador++
                    break
                }
            }
        }

        //return score / wordsToSearch.size
        return score / contador
    }


}



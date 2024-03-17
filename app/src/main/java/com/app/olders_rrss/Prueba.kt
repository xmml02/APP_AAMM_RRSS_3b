package com.app.olders_rrss

import kotlin.math.exp

fun main() {
    val userText =
        "Hola, me llamo Juan y me gustaría saber si puedo hacer una reserva para el día de mañana"
    val searchWords = "hola reserva para mañana"

    val score = CalcularWordsScore(userText, searchWords)
    println("Score: $score")
}

fun CalcularWordsScore(userText: String, searchWords: String): Double {

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



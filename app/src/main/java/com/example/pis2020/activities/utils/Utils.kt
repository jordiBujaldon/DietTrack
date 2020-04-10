package com.example.pis2020.activities.utils

import java.util.*

fun getCalendarioNumerosMes(calendario: Calendar): List<Int> {
    val ultimoDiaMes = calendario.getActualMaximum(Calendar.DAY_OF_MONTH)
    val hoy = calendario.get(Calendar.DAY_OF_MONTH)

    var idx = hoy
    val calendarioNumMes = mutableListOf<Int>()

    while (idx <= ultimoDiaMes) {
        calendarioNumMes.add(idx)
        idx++
    }

    return calendarioNumMes
}

fun getCalendarioDiasSemana(calendario: Calendar): List<String> {
    val ultimoDiaMes = calendario.getActualMaximum(Calendar.DAY_OF_MONTH)
    val hoy = calendario.get(Calendar.DAY_OF_MONTH)

    var idx = hoy
    var diaSemana = calendario.get(Calendar.DAY_OF_WEEK)

    val calendarioDiasSem = mutableListOf<String>()

    while (idx <= ultimoDiaMes) {
        if (diaSemana == 8) {
            diaSemana = 1
        }

        calendarioDiasSem.add(convertirDiaAString(diaSemana))

        diaSemana++
        idx++
    }

    return calendarioDiasSem
}

private fun convertirDiaAString(dia: Int): String {
    return when(dia) {
        1 -> "Domingo"
        2 -> "Lunes"
        3 -> "Martes"
        4 -> "Miercoles"
        5 -> "Jueves"
        6 -> "Viernes"
        7 -> "Sabado"
        else -> ""
    }
}
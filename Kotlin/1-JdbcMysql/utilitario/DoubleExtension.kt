package br.com.mygamesforum.games.utilitario

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

fun Double.formatoComDuasCasasDecimais(): Double {
    val decimalFomat = DecimalFormat("#.00", DecimalFormatSymbols(Locale.US))
    return decimalFomat.format(this).toDouble()
}
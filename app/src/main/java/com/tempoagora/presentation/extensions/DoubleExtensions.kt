package com.tempoagora.presentation.extensions

import java.text.DecimalFormat

fun Double.convertMsToKmh(): String {
    val kmh = this * 3.6
    val decimalFormat = DecimalFormat("#.##")
    return decimalFormat.format(kmh)
}
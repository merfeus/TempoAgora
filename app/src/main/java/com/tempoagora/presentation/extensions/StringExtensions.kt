package com.tempoagora.presentation.extensions

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun String.convertDateFormat(inputPattern: String, outputPattern: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern(inputPattern)
    val outputFormatter = DateTimeFormatter.ofPattern(outputPattern)

    val date: LocalDate = LocalDate.parse(this, inputFormatter)
    return date.format(outputFormatter)
}
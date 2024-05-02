package com.acuon.moviesapp.utils.extensions

import com.acuon.moviesapp.common.Constants
import java.text.SimpleDateFormat
import java.util.Locale

fun String?.serverToPrettyDate(
    currentFormat: String,
    targetFormat: String = ""
): String {
    if (this.isNullOrEmpty()) {
        return ""
    }

    val inputFormat = SimpleDateFormat(currentFormat, Locale.getDefault())
    val outputFormat = SimpleDateFormat(targetFormat, Locale.getDefault())

    return try {
        val date = inputFormat.parse(this)
        outputFormat.format(date)
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}
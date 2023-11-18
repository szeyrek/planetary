package com.scz.planetary.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtil {
    fun getToday() = System.currentTimeMillis().convertMillisToDate()
}

fun Long.convertMillisToDate(): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale("en"))
    return formatter.format(Date(this))
}

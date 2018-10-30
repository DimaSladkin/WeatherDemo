package com.sladkin.weatherdemo.utils

import java.text.SimpleDateFormat
import java.util.*

private const val ISO8601_PATTERN = "yyyy-MM-dd'T'HH:mm:ss"
private const val ISO8601Z_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
private val UTC = TimeZone.getTimeZone("UTC")

fun Date.getFormatedDate(): String {
    return SimpleDateFormat("yyyy/MM/dd")
            .apply { this.timeZone = TimeZone.getDefault() }
            .format(this)
}

fun String.getDateFromFormat(): Date {
    return SimpleDateFormat("yyyy-MM-dd")
            .parse(this)
}

fun String.parseIsoToDate(): Date {
    return SimpleDateFormat(ISO8601_PATTERN, Locale.getDefault()).apply { timeZone = UTC }
            .parse(this.substringBefore("."))
}

fun Date.getHour(): String {
    return SimpleDateFormat("HH::mm").format(this)
}

fun Date.getDaily(): String {
    return SimpleDateFormat("MM/dd").format(this)
}
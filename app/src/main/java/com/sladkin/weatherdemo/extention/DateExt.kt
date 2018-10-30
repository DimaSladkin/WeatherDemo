package com.sladkin.weatherdemo.extention

import java.text.SimpleDateFormat
import java.util.*

fun Date.getFormatedDate(): String {
    return SimpleDateFormat("yyyy/MM/dd")
            .apply { this.timeZone = TimeZone.getDefault() }
            .format(this)
}
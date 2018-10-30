package com.sladkin.weatherdemo.utils

import com.sladkin.weatherdemo.R

fun getResourceForString(abbr: String?): Int {
    return when (abbr) {
        "sn" -> R.drawable.ic_sn
        "sl" -> R.drawable.ic_sl
        "h" -> R.drawable.ic_h
        "t" -> R.drawable.ic_hr
        "lr" -> R.drawable.ic_lr
        "s" -> R.drawable.ic_s
        "hc" -> R.drawable.ic_hc
        "lc" -> R.drawable.ic_lc
        "c" -> R.drawable.ic_c
        else -> R.drawable.ic_s
    }
}
package com.sladkin.weatherdemo.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherDbModel(
        val type: Int? = null,
        val date: String? = null,
        val state: String? = null,
        val stateAbr: String? = null,
        val windSpeed: Float? = null,
        val compasPoint: String? = null,
        val minTemp: Int? = null,
        val maxTemp: Int? = null,
        val theTemp: Int? = null,
        val airPresure: Float? = null,
        val humidity: Float? = null,
        val visibility: Float? = null
) {
    @PrimaryKey(autoGenerate = true)
    var weatherId: Int = 0

    companion object {
        const val HOURLY_TYPE = 1
        const val DAILY_TYPE = 2
    }
}
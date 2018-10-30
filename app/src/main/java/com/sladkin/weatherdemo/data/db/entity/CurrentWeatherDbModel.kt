package com.sladkin.weatherdemo.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class CurrentWeatherDbModel(
        var title: String? = null,
        val locationType: String? = null,
        val sunrise: String? = null,
        val sunset: String? = null,
        @Embedded
        val weather: WeatherDbModel? = null
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
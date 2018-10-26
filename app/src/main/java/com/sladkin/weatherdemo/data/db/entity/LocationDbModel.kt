package com.sladkin.weatherdemo.data.db.entity

import androidx.room.Entity

@Entity
data class LocationDbModel(
        val title: String,
        val locationType: String,
        val lattLng: String,
        val time: String,
        val sunrise: String,
        val sunset: String,
        val timezone: String
)
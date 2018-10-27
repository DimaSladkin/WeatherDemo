package com.sladkin.weatherdemo.domain.entity

data class WeatherModel(
        val date: String?,
        val state: String?,
        val stateAbr: String?,
        val windSpeed: Float?,
        val compasPoint: String?,
        val minTemp: Int?,
        val maxTemp: Int?,
        val theTemp: Int?,
        val airPresure: Float?,
        val humidity: Float?,
        val visibility: Float?
)
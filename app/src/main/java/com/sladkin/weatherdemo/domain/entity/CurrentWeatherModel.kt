package com.sladkin.weatherdemo.domain.entity

data class CurrentWeatherModel(
        val title: String?,
        val type: String?,
        val sunrise: String?,
        val sunset: String?,
        val weather: WeatherModel?
)
package com.sladkin.weatherdemo.data.network.entity

import com.google.gson.annotations.SerializedName

data class WeatherApiModel(
        @SerializedName("title") val title: String,
        @SerializedName("location_type") val locationType: String,
        @SerializedName("latt_long") val lattLng: String,
        @SerializedName("time") val time: String,
        @SerializedName("sun_rise") val sunrise: String,
        @SerializedName("sun_set") val sunset: String,
        @SerializedName("timezone_name") val timezone: String,
        @SerializedName("consolidated_weather")
        val consolidatedWeather: List<ConsolidatedWeatherApiModel>
)
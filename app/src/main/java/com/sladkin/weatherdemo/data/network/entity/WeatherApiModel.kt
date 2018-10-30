package com.sladkin.weatherdemo.data.network.entity

import com.google.gson.annotations.SerializedName

data class WeatherApiModel(
        @SerializedName("id") val id: Long,
        @SerializedName("applicable_date") val date: String,
        @SerializedName("weather_state_name") val state: String,
        @SerializedName("weather_state_abbr") val stateAbr: String,
        @SerializedName("wind_speed") val windSpeed: Float,
        @SerializedName("wind_direction") val windDirection: Float,
        @SerializedName("wind_direction_compass") val compasPoint: String,
        @SerializedName("min_temp") val minTemp: Float,
        @SerializedName("max_temp") val maxTemp: Float,
        @SerializedName("the_temp") val theTemp: Float,
        @SerializedName("air_pressure") val airPresure: Float,
        @SerializedName("humidity") val humidity: Float,
        @SerializedName("visibility") val visibility: Float
)

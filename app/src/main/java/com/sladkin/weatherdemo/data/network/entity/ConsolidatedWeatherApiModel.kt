package com.sladkin.weatherdemo.data.network.entity

import com.google.gson.annotations.SerializedName

data class ConsolidatedWeatherApiModel(
        @SerializedName("id") val id: Int,
        @SerializedName("applicable_date") val date: String,
        @SerializedName("weather_state_name") val state: String,
        @SerializedName("weather_state_abbr") val stateAbr: String,
        @SerializedName("wind_speed") val windSpeed: Float,
        @SerializedName("wind_direction") val windDirection: Float,
        @SerializedName("wind_direction_compass") val compasPoint: String,
        @SerializedName("min_temp") val minTemp: Int,
        @SerializedName("max_temp") val maxTemp: Int,
        @SerializedName("the_temp") val theTemp: Int,
        @SerializedName("air_pressure") val airPresure: Float,
        @SerializedName("humidity") val humidity: Float,
        @SerializedName("visibility") val visibility: Float
)

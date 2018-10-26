package com.sladkin.weatherdemo.data.network.entity

import com.google.gson.annotations.SerializedName

data class WeatherLocationApiModel(
        @SerializedName("title") val title: String,
        @SerializedName("location_type") val locType: String,
        @SerializedName("latt_long") val latLng: String,
        @SerializedName("woeid") val woeid: Int,
        @SerializedName("distance") val distance: Int
)
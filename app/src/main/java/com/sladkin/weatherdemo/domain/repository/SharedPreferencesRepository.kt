package com.sladkin.weatherdemo.domain.repository

import com.sladkin.weatherdemo.data.network.entity.WeatherLocationApiModel
import com.sladkin.weatherdemo.domain.entity.LocationModel

interface SharedPreferencesRepository {

    fun saveLocation(location: WeatherLocationApiModel)

    fun getLocation(): LocationModel
}
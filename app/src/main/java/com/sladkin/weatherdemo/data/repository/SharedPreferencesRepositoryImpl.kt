package com.sladkin.weatherdemo.data.repository

import android.content.Context
import android.preference.PreferenceManager
import com.sladkin.weatherdemo.data.network.entity.WeatherLocationApiModel
import com.sladkin.weatherdemo.domain.entity.LocationModel
import com.sladkin.weatherdemo.domain.repository.SharedPreferencesRepository

class SharedPreferencesRepositoryImpl(context: Context) : SharedPreferencesRepository {

    companion object {
        private const val LOCATION_TITLE_KEY = "locationTitleKey"
        private const val LOCATION_TYPE_KEY = "locationTitleKey"
    }

    private val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    override fun saveLocation(location: WeatherLocationApiModel) {
        prefs.edit().putString(LOCATION_TITLE_KEY, location.title).apply()
        prefs.edit().putString(LOCATION_TYPE_KEY, location.locType).apply()
    }

    override fun getLocation(): LocationModel {
        val type = prefs.getString(LOCATION_TYPE_KEY, "") ?: ""
        val title = prefs.getString(LOCATION_TITLE_KEY, "") ?: ""
        return LocationModel(title, type, "", "")
    }
}
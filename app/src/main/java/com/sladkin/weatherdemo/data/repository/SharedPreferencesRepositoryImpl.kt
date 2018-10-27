package com.sladkin.weatherdemo.data.repository

import android.content.Context
import android.preference.PreferenceManager
import com.sladkin.weatherdemo.domain.repository.SharedPreferencesRepository

class SharedPreferencesRepositoryImpl(context: Context) : SharedPreferencesRepository {

    companion object {
        private const val LAST_UPDATED_TIME_KEY = "lastUpdatedTime"
    }

    private val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    override fun getLastUpdatedTime(): Long {
        return prefs.getLong(LAST_UPDATED_TIME_KEY, 0L)
    }

    override fun saveLastUpdatedTime(lastUpdatedTime: Long) {
        prefs.edit().putLong(LAST_UPDATED_TIME_KEY, lastUpdatedTime).apply()
    }
}
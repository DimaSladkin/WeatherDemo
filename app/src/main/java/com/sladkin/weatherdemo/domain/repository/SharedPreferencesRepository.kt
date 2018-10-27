package com.sladkin.weatherdemo.domain.repository

interface SharedPreferencesRepository {

    fun saveLastUpdatedTime(lastUpdatedTime: Long)

    fun getLastUpdatedTime(): Long
}
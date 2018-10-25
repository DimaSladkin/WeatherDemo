package com.sladkin.weatherdemo

import android.app.Application
import timber.log.Timber

class WeatherApp: Application() {

    override fun onCreate() {
        super.onCreate()
        setUpTimber()
    }

    private fun setUpTimber() {
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}
package com.sladkin.weatherdemo

import android.app.Application
import com.sladkin.weatherdemo.di.appModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()
        setUpTimber()
        startKoin(this, appModule)
    }

    private fun setUpTimber() {
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}
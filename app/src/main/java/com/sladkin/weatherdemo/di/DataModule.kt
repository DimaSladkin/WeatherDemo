package com.sladkin.weatherdemo.di

import androidx.room.Room
import com.sladkin.weatherdemo.data.db.WeatherDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

private const val DATABASE_NAME = "weather_database"

val dataModule = module {
    single {
        Room.databaseBuilder(androidContext(), WeatherDatabase::class.java, DATABASE_NAME)
                .build()
    }

    single { get<WeatherDatabase>().getWeatherDao() }
}
package com.sladkin.weatherdemo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sladkin.weatherdemo.data.db.dao.WeatherDao
import com.sladkin.weatherdemo.data.db.entity.CurrentWeatherDbModel
import com.sladkin.weatherdemo.data.db.entity.WeatherDbModel

@Database(
        entities = [CurrentWeatherDbModel::class, WeatherDbModel::class], version = 1
)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun getWeatherDao(): WeatherDao
}
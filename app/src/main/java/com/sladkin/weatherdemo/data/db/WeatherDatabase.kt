package com.sladkin.weatherdemo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sladkin.weatherdemo.data.db.dao.WeatherDao
import com.sladkin.weatherdemo.data.db.entity.LocationDbModel
import com.sladkin.weatherdemo.data.db.entity.WeatherDbModel

@Database(
        entities = [WeatherDbModel::class, LocationDbModel::class], version = 1
)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun getWeatherDao(): WeatherDao
}
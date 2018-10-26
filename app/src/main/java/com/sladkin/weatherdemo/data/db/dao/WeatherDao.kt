package com.sladkin.weatherdemo.data.db.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.sladkin.weatherdemo.data.db.entity.LocationDbModel
import com.sladkin.weatherdemo.data.db.entity.WeatherDbModel
import io.reactivex.Single

interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(weather: List<WeatherDbModel>)

    @Query("DELETE FROM weatherdbmodel")
    fun deleteAllWeather()

    @Transaction
    fun saveWeather(weather: List<WeatherDbModel>) {
        deleteAllWeather()
        insertWeather(weather)
    }

    @Query("SELECT * FROM weatherdbmodel")
    fun getAllWeather(): Single<WeatherDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocation(weather: List<LocationDbModel>)

    @Query("SELECT * FROM locationdbmodel")
    fun getLocation(): Single<LocationDbModel>

}
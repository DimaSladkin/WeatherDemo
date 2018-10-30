package com.sladkin.weatherdemo.data.db.dao

import androidx.room.*
import com.sladkin.weatherdemo.data.db.entity.CurrentWeatherDbModel
import com.sladkin.weatherdemo.data.db.entity.WeatherDbModel
import io.reactivex.Flowable

@Dao
interface WeatherDao {

    @Transaction
    fun saveCurrentWeather(weather: CurrentWeatherDbModel) {
        deleteCurrentWeather()
        insertCurrentWeather(weather)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrentWeather(currentWeatherDbModel: CurrentWeatherDbModel)

    @Query("DELETE FROM currentweatherdbmodel")
    fun deleteCurrentWeather()

    @Query("DELETE FROM weatherdbmodel")
    fun deleteAllWeather()

    @Query("SELECT * FROM currentweatherdbmodel")
    fun getCurrentWeather(): Flowable<CurrentWeatherDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(weather: List<WeatherDbModel>)

    @Query("SELECT * FROM weatherdbmodel WHERE type = :type")
    fun getWeather(type: Int): Flowable<List<WeatherDbModel>>

}
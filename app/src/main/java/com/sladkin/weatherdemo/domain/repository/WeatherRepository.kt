package com.sladkin.weatherdemo.domain.repository

import com.sladkin.weatherdemo.domain.entity.CurrentWeatherModel
import com.sladkin.weatherdemo.domain.entity.WeatherModel
import io.reactivex.Completable
import io.reactivex.Observable

interface WeatherRepository {

    fun getCurrentWeather(): Observable<CurrentWeatherModel>

    fun getHourlyWeather(): Observable<List<WeatherModel>>

    fun getDailyWeather(): Observable<List<WeatherModel>>

    fun forceUpdate(): Completable
}
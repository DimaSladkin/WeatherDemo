package com.sladkin.weatherdemo.data.network.api

import com.sladkin.weatherdemo.data.network.entity.WeatherApiModel
import com.sladkin.weatherdemo.data.network.entity.CurrentWeatherApiModel
import com.sladkin.weatherdemo.data.network.entity.WeatherLocationApiModel
import io.reactivex.Single
import retrofit2.http.*

interface WeatherApi {

    @GET("/api/location/search/")
    fun getLocation(@Query("lattlong") latLng: String): Single<List<WeatherLocationApiModel>>

    @GET("/api/location/{woeid}/")
    fun getCurrentWeather(@Path("woeid") woeid: Int): Single<CurrentWeatherApiModel>

    @GET("/api/location/{woeid}/{date}/")
    fun getHourlyWeather(@Path("woeid") woeid: Int,
                         @Path("date") date: String): Single<List<WeatherApiModel>>

}
package com.sladkin.weatherdemo.data.network.api

import com.sladkin.weatherdemo.data.network.entity.ConsolidatedWeatherApiModel
import com.sladkin.weatherdemo.data.network.entity.WeatherApiModel
import com.sladkin.weatherdemo.data.network.entity.WeatherLocationApiModel
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherApi {

    @GET("/api/location/search/")
    fun getLocation(@Field("lattlong") latLng: String): Single<WeatherLocationApiModel>

    @GET("/api/location/{woeid}/")
    fun getCurrentWeather(@Path("woeid") woeid: Int): Single<WeatherApiModel>

    @GET("/api/location/{woeid}/{date}/")
    fun getHourlyWeather(@Path("woeid") woeid: Int,
                         @Path("date") date: String): Single<List<ConsolidatedWeatherApiModel>>
}
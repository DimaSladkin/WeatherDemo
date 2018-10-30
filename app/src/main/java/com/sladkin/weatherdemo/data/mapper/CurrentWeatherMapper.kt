package com.sladkin.weatherdemo.data.mapper

import com.sladkin.weatherdemo.data.db.entity.CurrentWeatherDbModel
import com.sladkin.weatherdemo.data.network.entity.CurrentWeatherApiModel
import com.sladkin.weatherdemo.domain.entity.CurrentWeatherModel

class CurrentWeatherMapper(private val weatherMapper: WeatherMapper) :
        BaseMapper<CurrentWeatherApiModel, CurrentWeatherDbModel, CurrentWeatherModel>() {

    override fun apiModelToDomain(apiModel: CurrentWeatherApiModel): CurrentWeatherModel {
        return CurrentWeatherModel(apiModel.title, apiModel.locationType,
                apiModel.sunrise, apiModel.sunset,
                apiModel.weather.firstOrNull()?.let { weatherMapper.apiModelToDomain(it) })
    }

    override fun apiModelToDb(apiModel: CurrentWeatherApiModel): CurrentWeatherDbModel {
        return CurrentWeatherDbModel(apiModel.title, apiModel.locationType,
                apiModel.sunrise, apiModel.sunset,
                apiModel.weather.firstOrNull()?.let { weatherMapper.apiModelToDb(it) })
    }

    override fun dbModelToDomain(dbModel: CurrentWeatherDbModel): CurrentWeatherModel {
        return CurrentWeatherModel(dbModel.title, dbModel.locationType, dbModel.sunrise,
                dbModel.sunset, dbModel.weather?.let { weatherMapper.dbModelToDomain(it) })
    }
}
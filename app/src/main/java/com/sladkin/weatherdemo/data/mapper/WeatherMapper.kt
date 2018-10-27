package com.sladkin.weatherdemo.data.mapper

import com.sladkin.weatherdemo.data.db.entity.WeatherDbModel
import com.sladkin.weatherdemo.data.network.entity.ConsolidatedWeatherApiModel
import com.sladkin.weatherdemo.domain.entity.WeatherModel

class WeatherMapper : BaseMapper<ConsolidatedWeatherApiModel, WeatherDbModel, WeatherModel>() {

    override fun apiModelToDomain(apiModel: ConsolidatedWeatherApiModel): WeatherModel {
        return WeatherModel(apiModel.date, apiModel.state, apiModel.stateAbr, apiModel.windSpeed,
                apiModel.compasPoint, apiModel.minTemp, apiModel.maxTemp, apiModel.theTemp,
                apiModel.airPresure, apiModel.humidity, apiModel.visibility)
    }

    override fun apiModelToDb(apiModel: ConsolidatedWeatherApiModel): WeatherDbModel {
        return WeatherDbModel(apiModel.id, apiModel.date, apiModel.state, apiModel.stateAbr,
                apiModel.windSpeed, apiModel.compasPoint, apiModel.minTemp, apiModel.maxTemp,
                apiModel.theTemp, apiModel.airPresure, apiModel.humidity, apiModel.visibility)
    }

    override fun dbModelToDomain(dbModel: WeatherDbModel): WeatherModel {
        return WeatherModel(dbModel.date, dbModel.state, dbModel.stateAbr,
                dbModel.windSpeed, dbModel.compasPoint, dbModel.minTemp,
                dbModel.maxTemp, dbModel.theTemp, dbModel.airPresure, dbModel.humidity,
                dbModel.visibility)
    }
}
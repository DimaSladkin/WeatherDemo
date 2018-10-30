package com.sladkin.weatherdemo.data.mapper

import com.sladkin.weatherdemo.data.db.entity.WeatherDbModel
import com.sladkin.weatherdemo.data.network.entity.WeatherApiModel
import com.sladkin.weatherdemo.domain.entity.WeatherModel

class WeatherMapper : BaseMapper<WeatherApiModel, WeatherDbModel, WeatherModel>() {

    override fun apiModelToDomain(apiModel: WeatherApiModel): WeatherModel {
        return WeatherModel(apiModel.date, apiModel.state, apiModel.stateAbr, apiModel.windSpeed,
                apiModel.compasPoint, apiModel.minTemp.toInt(), apiModel.maxTemp.toInt(),
                apiModel.theTemp.toInt(), apiModel.airPresure, apiModel.humidity, apiModel.visibility)
    }

    override fun apiModelToDb(apiModel: WeatherApiModel): WeatherDbModel {
        return WeatherDbModel(WeatherDbModel.DAILY_TYPE, apiModel.date, apiModel.state, apiModel.stateAbr,
                apiModel.windSpeed, apiModel.compasPoint, apiModel.minTemp.toInt(), apiModel.maxTemp.toInt(),
                apiModel.theTemp.toInt(), apiModel.airPresure, apiModel.humidity, apiModel.visibility)
    }

    fun apiModelToDb(apiModel: WeatherApiModel, type: Int): WeatherDbModel {
        return WeatherDbModel(type, apiModel.date, apiModel.state, apiModel.stateAbr,
                apiModel.windSpeed, apiModel.compasPoint, apiModel.minTemp.toInt(), apiModel.maxTemp.toInt(),
                apiModel.theTemp.toInt(), apiModel.airPresure, apiModel.humidity, apiModel.visibility)
    }

    override fun dbModelToDomain(dbModel: WeatherDbModel): WeatherModel {
        return WeatherModel(dbModel.date, dbModel.state, dbModel.stateAbr,
                dbModel.windSpeed, dbModel.compasPoint, dbModel.minTemp,
                dbModel.maxTemp, dbModel.theTemp, dbModel.airPresure, dbModel.humidity,
                dbModel.visibility)
    }
}
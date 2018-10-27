package com.sladkin.weatherdemo.data.mapper

import com.sladkin.weatherdemo.data.db.entity.LocationDbModel
import com.sladkin.weatherdemo.data.network.entity.WeatherApiModel
import com.sladkin.weatherdemo.domain.entity.LocationModel

class LocationMapper : BaseMapper<WeatherApiModel, LocationDbModel, LocationModel>() {

    override fun apiModelToDomain(apiModel: WeatherApiModel): LocationModel {
        return LocationModel(apiModel.title, apiModel.locationType,
                apiModel.sunrise, apiModel.sunset)
    }

    override fun apiModelToDb(apiModel: WeatherApiModel): LocationDbModel {
        return LocationDbModel(apiModel.title, apiModel.locationType, apiModel.lattLng,
                apiModel.time, apiModel.sunrise, apiModel.sunset, apiModel.timezone)
    }

    override fun dbModelToDomain(dbModel: LocationDbModel): LocationModel {
        return LocationModel(dbModel.title, dbModel.locationType, dbModel.sunrise, dbModel.sunset)
    }
}
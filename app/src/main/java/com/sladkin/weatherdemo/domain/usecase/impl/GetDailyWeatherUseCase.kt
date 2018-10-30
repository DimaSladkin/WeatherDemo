package com.sladkin.weatherdemo.domain.usecase.impl

import com.sladkin.weatherdemo.domain.entity.WeatherModel
import com.sladkin.weatherdemo.domain.repository.WeatherRepository
import com.sladkin.weatherdemo.domain.usecase.UseCaseObservable
import com.sladkin.weatherdemo.domain.usecase.scheduler.UseCaseSchedulers
import io.reactivex.Observable

class GetDailyWeatherUseCase(useCaseSchedulers: UseCaseSchedulers,
                             private val weatherRepository: WeatherRepository)
    : UseCaseObservable<Unit, List<WeatherModel>>(useCaseSchedulers) {

    override fun buildUseCase(parameter: Unit): Observable<List<WeatherModel>> {
        return weatherRepository.getDailyWeather()
    }
}
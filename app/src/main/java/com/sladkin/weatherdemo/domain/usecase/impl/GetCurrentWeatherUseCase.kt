package com.sladkin.weatherdemo.domain.usecase.impl

import com.sladkin.weatherdemo.domain.entity.CurrentWeatherModel
import com.sladkin.weatherdemo.domain.repository.WeatherRepository
import com.sladkin.weatherdemo.domain.usecase.UseCaseObservable
import com.sladkin.weatherdemo.domain.usecase.scheduler.UseCaseSchedulers
import io.reactivex.Observable

class GetCurrentWeatherUseCase(useCaseSchedulers: UseCaseSchedulers,
                               private val weatherRepository: WeatherRepository)
    : UseCaseObservable<Unit, CurrentWeatherModel>(useCaseSchedulers) {

    override fun buildUseCase(parameter: Unit): Observable<CurrentWeatherModel> {
        return weatherRepository.getCurrentWeather()
    }
}
package com.sladkin.weatherdemo.domain.usecase.impl

import com.sladkin.weatherdemo.domain.repository.WeatherRepository
import com.sladkin.weatherdemo.domain.usecase.UseCaseCompletable
import com.sladkin.weatherdemo.domain.usecase.scheduler.UseCaseSchedulers
import io.reactivex.Completable

class ForceUpdateUseCase(useCaseSchedulers: UseCaseSchedulers,
                         private val weatherRepository: WeatherRepository)
    : UseCaseCompletable<Unit>(useCaseSchedulers) {

    override fun buildUseCase(parameter: Unit): Completable {
        return weatherRepository.forceUpdate()
    }
}
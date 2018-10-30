package com.sladkin.weatherdemo.presentation.current

import com.sladkin.weatherdemo.domain.entity.CurrentWeatherModel
import com.sladkin.weatherdemo.presentation.Presenter

interface CurrentWeatherPresenter<T> : Presenter<T> {

    interface CurrentWeatherView {
        fun setCurrentWeather(currentWeatherModel: CurrentWeatherModel)

        fun hideLoading()

        fun onError(throwable: Throwable)
    }

    fun onViewCreated()

    fun onSwipeToRefresh()
}
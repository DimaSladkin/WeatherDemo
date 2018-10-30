package com.sladkin.weatherdemo.presentation.hourly

import com.sladkin.weatherdemo.domain.entity.WeatherModel
import com.sladkin.weatherdemo.presentation.Presenter

interface HourlyPresenter<T>: Presenter<T> {

    interface HourlyView {
        fun onError(e: Throwable)

        fun showHourlyList(list: List<WeatherModel>)
    }

    fun onViewCreated()
}
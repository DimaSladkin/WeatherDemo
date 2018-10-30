package com.sladkin.weatherdemo.presentation.daily

import com.sladkin.weatherdemo.domain.entity.WeatherModel
import com.sladkin.weatherdemo.presentation.Presenter

interface DailyPresenter<T>: Presenter<T> {

    interface DailyView {
        fun onError(e: Throwable)

        fun showDailyList(list: List<WeatherModel>)
    }

    fun onViewCreated()
}
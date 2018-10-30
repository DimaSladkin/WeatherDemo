package com.sladkin.weatherdemo.di

import com.sladkin.weatherdemo.domain.usecase.impl.ForceUpdateUseCase
import com.sladkin.weatherdemo.domain.usecase.impl.GetCurrentWeatherUseCase
import com.sladkin.weatherdemo.domain.usecase.impl.GetDailyWeatherUseCase
import com.sladkin.weatherdemo.domain.usecase.impl.GetHourlyWeatherUseCase
import com.sladkin.weatherdemo.extention.rx.scheduler.IoMainSchedulers
import com.sladkin.weatherdemo.extention.rx.scheduler.UseCaseSchedulers
import com.sladkin.weatherdemo.presentation.current.CurrentWeatherPresenter
import com.sladkin.weatherdemo.presentation.current.CurrentWeatherPresenterImpl
import com.sladkin.weatherdemo.presentation.daily.DailyPresenter
import com.sladkin.weatherdemo.presentation.daily.DailyPresenterImpl
import com.sladkin.weatherdemo.presentation.hourly.HourlyPresenter
import com.sladkin.weatherdemo.presentation.hourly.HourlyPresenterImpl
import org.koin.dsl.module.module

const val CURRENT_SCOPE = "current"
const val DAILY_SCOPE = "daily"
const val HOURLY_SCOPE = "hourly"

val presentationModule = module {

    single<UseCaseSchedulers> { IoMainSchedulers() }

    scope<CurrentWeatherPresenter<CurrentWeatherPresenter.CurrentWeatherView>>(CURRENT_SCOPE) {
        CurrentWeatherPresenterImpl(get(), get())
    }

    scope<HourlyPresenter<HourlyPresenter.HourlyView>>(HOURLY_SCOPE) {
        HourlyPresenterImpl(get())
    }

    scope<DailyPresenter<DailyPresenter.DailyView>>(DAILY_SCOPE) {
        DailyPresenterImpl(get())
    }

    scope(CURRENT_SCOPE) { GetCurrentWeatherUseCase(get(), get()) }
    scope(CURRENT_SCOPE) { ForceUpdateUseCase(get(), get()) }
    scope(DAILY_SCOPE) { GetDailyWeatherUseCase(get(), get()) }
    scope(HOURLY_SCOPE) { GetHourlyWeatherUseCase(get(), get()) }

}
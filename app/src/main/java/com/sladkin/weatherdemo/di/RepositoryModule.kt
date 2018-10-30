package com.sladkin.weatherdemo.di

import com.sladkin.weatherdemo.data.mapper.CurrentWeatherMapper
import com.sladkin.weatherdemo.data.mapper.WeatherMapper
import com.sladkin.weatherdemo.data.repository.SharedPreferencesRepositoryImpl
import com.sladkin.weatherdemo.data.repository.WeatherRepositoryImpl
import com.sladkin.weatherdemo.domain.repository.SharedPreferencesRepository
import com.sladkin.weatherdemo.domain.repository.WeatherRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider

val repositoryModule = module {

    single<SharedPreferencesRepository> {
        SharedPreferencesRepositoryImpl(androidContext())
    }

    single<WeatherRepository> {
        WeatherRepositoryImpl(androidContext(), get(), get(), get(), get(), get(), get())
    }

    single { CurrentWeatherMapper(get()) }
    single { WeatherMapper() }
    single { ReactiveLocationProvider(androidContext()) }
}
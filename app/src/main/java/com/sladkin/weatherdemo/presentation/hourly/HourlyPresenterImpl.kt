package com.sladkin.weatherdemo.presentation.hourly

import com.sladkin.weatherdemo.domain.entity.WeatherModel
import com.sladkin.weatherdemo.domain.usecase.impl.GetHourlyWeatherUseCase
import com.sladkin.weatherdemo.extention.rx.observer.DefaultObserver
import io.reactivex.disposables.CompositeDisposable

class HourlyPresenterImpl<T : HourlyPresenter.HourlyView>(
        private val getHourlyWeatherUseCase: GetHourlyWeatherUseCase
) : HourlyPresenter<T> {

    private val disposables = CompositeDisposable()
    private var view: T? = null

    override fun onViewCreated() {
        getHourlyWeatherUseCase.execute(Unit,
                object : DefaultObserver<List<WeatherModel>>(disposables) {
                    override fun onNext(t: List<WeatherModel>) {
                        view?.showHourlyList(t)
                    }

                    override fun onError(e: Throwable) {
                        view?.onError(e)
                    }
                })
    }

    override fun setView(view: T) {
        this.view = view
    }

    override fun destroy() {
        this.view = null
        disposables.dispose()
    }
}
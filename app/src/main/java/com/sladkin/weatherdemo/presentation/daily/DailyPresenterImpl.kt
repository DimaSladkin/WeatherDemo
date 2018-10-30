package com.sladkin.weatherdemo.presentation.daily

import com.sladkin.weatherdemo.domain.entity.WeatherModel
import com.sladkin.weatherdemo.domain.usecase.impl.GetDailyWeatherUseCase
import com.sladkin.weatherdemo.utils.rx.observer.DefaultObserver
import io.reactivex.disposables.CompositeDisposable

class DailyPresenterImpl<T : DailyPresenter.DailyView>(
        private val getDailyWeatherUseCase: GetDailyWeatherUseCase) : DailyPresenter<T> {
    private val disposables = CompositeDisposable()

    private var view: T? = null

    override fun onViewCreated() {
        getDailyWeatherUseCase.execute(Unit,
                object : DefaultObserver<List<WeatherModel>>(disposables) {
                    override fun onNext(t: List<WeatherModel>) {
                        view?.showDailyList(t)
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
        view = null
    }
}
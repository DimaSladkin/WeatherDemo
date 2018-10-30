package com.sladkin.weatherdemo.presentation.current

import com.sladkin.weatherdemo.domain.entity.CurrentWeatherModel
import com.sladkin.weatherdemo.domain.usecase.impl.GetCurrentWeatherUseCase
import com.sladkin.weatherdemo.extention.rx.observer.DefaultObserver
import io.reactivex.disposables.CompositeDisposable

class CurrentWeatherPresenterImpl<T : CurrentWeatherPresenter.CurrentWeatherView>(
        private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
) : CurrentWeatherPresenter<T> {

    private var view: T? = null

    private val disposables = CompositeDisposable()

    override fun onViewCreated() {
        getCurrentWeatherUseCase.execute(Unit,
                object : DefaultObserver<CurrentWeatherModel>(disposables) {
                    override fun onNext(t: CurrentWeatherModel) {
                        view?.setCurrentWeather(t)
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
        disposables.dispose()
    }
}
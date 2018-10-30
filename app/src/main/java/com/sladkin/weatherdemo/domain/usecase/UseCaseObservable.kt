package com.sladkin.weatherdemo.domain.usecase

import com.sladkin.weatherdemo.extention.rx.scheduler.UseCaseSchedulers
import io.reactivex.Observable
import io.reactivex.Observer

abstract class UseCaseObservable<in P, R>(private val schedulers: UseCaseSchedulers) {

    protected abstract fun buildUseCase(parameter: P): Observable<R>

    fun execute(
            parameter: P,
            observer: Observer<R>
    ) {
        return buildUseCase(parameter)
                .observeOn(schedulers.observeOn)
                .subscribeOn(schedulers.subscribeOn)
                .subscribe(observer)
    }
}
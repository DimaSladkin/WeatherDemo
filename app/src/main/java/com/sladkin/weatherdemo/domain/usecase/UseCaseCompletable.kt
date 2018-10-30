package com.sladkin.weatherdemo.domain.usecase

import com.sladkin.weatherdemo.extention.rx.scheduler.UseCaseSchedulers
import io.reactivex.Completable
import io.reactivex.CompletableObserver

abstract class UseCaseCompletable<in P>(private val schedulers: UseCaseSchedulers) {

    protected abstract fun buildUseCase(parameter: P): Completable

    fun execute(
            parameter: P,
            observer: CompletableObserver
    ) {
        return buildUseCase(parameter)
                .observeOn(schedulers.observeOn)
                .subscribeOn(schedulers.subscribeOn)
                .subscribe(observer)
    }
}
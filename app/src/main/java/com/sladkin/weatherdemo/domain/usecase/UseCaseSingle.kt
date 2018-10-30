package com.sladkin.weatherdemo.domain.usecase

import com.sladkin.weatherdemo.extention.rx.scheduler.UseCaseSchedulers
import io.reactivex.Single
import io.reactivex.SingleObserver

abstract class UseCaseSingle<in P, R>(private val schedulers: UseCaseSchedulers) {

    protected abstract fun buildUseCase(parameter: P): Single<R>

    fun execute(
            parameter: P,
            observer: SingleObserver<R>
    ) {
        return buildUseCase(parameter)
                .observeOn(schedulers.observeOn)
                .subscribeOn(schedulers.subscribeOn)
                .subscribe(observer)
    }

}

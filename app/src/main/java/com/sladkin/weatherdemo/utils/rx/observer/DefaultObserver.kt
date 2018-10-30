package com.sladkin.weatherdemo.utils.rx.observer

import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class DefaultObserver<T>(
        private val disposables: CompositeDisposable
) : Observer<T> {
    override fun onComplete() {}

    override fun onSubscribe(d: Disposable) {
        disposables.add(d)
    }

    override fun onNext(t: T) {}

    override fun onError(e: Throwable) {}
}
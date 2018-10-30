package com.sladkin.weatherdemo.extention.rx.observer

import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class DefaultSingleObserver<T>(
  private val disposables: CompositeDisposable
) : SingleObserver<T> {

  override fun onSubscribe(d: Disposable) {
    disposables.add(d)
  }

  override fun onSuccess(t: T) {}

  override fun onError(e: Throwable) {}
}
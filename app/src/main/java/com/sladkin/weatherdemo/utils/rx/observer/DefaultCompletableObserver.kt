package com.sladkin.weatherdemo.utils.rx.observer

import io.reactivex.CompletableObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class DefaultCompletableObserver(
  private val disposables: CompositeDisposable
) : CompletableObserver {
  override fun onComplete() {}

  override fun onSubscribe(d: Disposable) {
    disposables.add(d)
  }

  override fun onError(e: Throwable) {}
}
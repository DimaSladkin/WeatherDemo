package com.sladkin.weatherdemo.extention.rx.scheduler

import io.reactivex.Scheduler

abstract class UseCaseSchedulers(
  val observeOn: Scheduler,
  val subscribeOn: Scheduler
)
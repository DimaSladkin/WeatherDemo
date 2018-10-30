package com.sladkin.weatherdemo.domain.usecase.scheduler

import io.reactivex.Scheduler

abstract class UseCaseSchedulers(
  val observeOn: Scheduler,
  val subscribeOn: Scheduler
)
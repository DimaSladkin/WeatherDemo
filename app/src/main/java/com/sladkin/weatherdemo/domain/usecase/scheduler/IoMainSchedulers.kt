package com.sladkin.weatherdemo.domain.usecase.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class IoMainSchedulers : UseCaseSchedulers(AndroidSchedulers.mainThread(), Schedulers.io())
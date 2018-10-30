package com.sladkin.weatherdemo.extention.rx.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class IoMainSchedulers : UseCaseSchedulers(AndroidSchedulers.mainThread(), Schedulers.io())
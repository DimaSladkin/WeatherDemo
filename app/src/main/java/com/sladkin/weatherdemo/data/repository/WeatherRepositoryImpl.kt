package com.sladkin.weatherdemo.data.repository

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import com.sladkin.weatherdemo.data.db.dao.WeatherDao
import com.sladkin.weatherdemo.data.db.entity.WeatherDbModel
import com.sladkin.weatherdemo.data.mapper.CurrentWeatherMapper
import com.sladkin.weatherdemo.data.mapper.WeatherMapper
import com.sladkin.weatherdemo.data.network.api.WeatherApi
import com.sladkin.weatherdemo.data.network.entity.CurrentWeatherApiModel
import com.sladkin.weatherdemo.domain.entity.CurrentWeatherModel
import com.sladkin.weatherdemo.domain.entity.WeatherModel
import com.sladkin.weatherdemo.domain.repository.SharedPreferencesRepository
import com.sladkin.weatherdemo.domain.repository.WeatherRepository
import com.sladkin.weatherdemo.exception.GpsNotEnabledException
import com.sladkin.weatherdemo.exception.MissingPermissionException
import com.sladkin.weatherdemo.extention.getFormatedDate
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider
import timber.log.Timber
import java.util.*

class WeatherRepositoryImpl(private val context: Context,
                            private val weatherDao: WeatherDao,
                            private val weatherApi: WeatherApi,
                            private val currentWeatherMapper: CurrentWeatherMapper,
                            private val weatherMapper: WeatherMapper,
                            private val rxLocation: ReactiveLocationProvider,
                            private val sharedPrefsRepository: SharedPreferencesRepository)
    : WeatherRepository {

    companion object {
        const val ONE_HOUR = 3600000
    }

    override fun getCurrentWeather(): Observable<CurrentWeatherModel> {
        Timber.i("get current weather")
        return checkForUpdate().andThen(getCurrentWeatherFromDb()).toObservable()
    }

    override fun getHourlyWeather(): Observable<List<WeatherModel>> {
        Timber.i("get hourly weather")
        return checkForUpdate().andThen(getHourlyWeatherFromDb()).toObservable()
    }

    override fun getDailyWeather(): Observable<List<WeatherModel>> {
        return checkForUpdate().andThen(getDailyWeatherFromDb()).toObservable()
    }

    override fun forceUpdate(): Completable {
        return getLocation()
                .flatMapCompletable {
                    getCurrentWeatherFromServer(it)
                            .subscribeOn(Schedulers.io())
                            .ignoreElement()
                            .mergeWith(
                                    getHourlyWeatherFromServer(it, Date().getFormatedDate())
                                            .subscribeOn(Schedulers.io())
                                            .ignoreElement()
                            )
                }
                .doOnComplete { sharedPrefsRepository.saveLastUpdatedTime(System.currentTimeMillis()) }
    }

    private fun getCurrentWeatherFromServer(woeid: Int): Single<CurrentWeatherModel> {
        Log.i("onx", "get current weather")
        return weatherApi.getCurrentWeather(woeid)
                .doOnEvent { t1, _ ->
                    t1?.let {
                        saveCurrentWeather(it)
                    }
                }
                .map { currentWeatherMapper.apiModelToDomain(it) }
    }

    private fun getHourlyWeatherFromServer(woeid: Int, date: String): Single<List<WeatherModel>> {
        return weatherApi.getHourlyWeather(woeid, date)
                .doOnEvent { t1, _ ->
                    t1?.let {
                        Timber.i("hourly weather from server - $t1")
                        weatherDao.saveWeather(
                                it.map { item ->
                                    Timber.i("map - $item to db")
                                    weatherMapper.apiModelToDb(item, WeatherDbModel.HOURLY_TYPE)
                                }
                        )
                    }
                }
                .map { list -> list.map { weatherMapper.apiModelToDomain(it) } }
    }

    private fun saveCurrentWeather(it: CurrentWeatherApiModel) {
        Timber.i("save weather - $it")
        weatherDao.saveCurrentWeather(currentWeatherMapper.apiModelToDb(it))
        weatherDao.saveWeather(
                it.weather.let { list ->
                    list.map { item ->
                        weatherMapper.apiModelToDb(item)
                    }
                }
        )
    }

    private fun getHourlyWeatherFromDb(): Flowable<List<WeatherModel>> {
        return weatherDao.getWeather(WeatherDbModel.HOURLY_TYPE)
                .map { list -> list.map { weatherMapper.dbModelToDomain(it) } }
    }

    private fun getDailyWeatherFromDb(): Flowable<List<WeatherModel>> {
        return weatherDao.getWeather(WeatherDbModel.HOURLY_TYPE)
                .map { list -> list.map { weatherMapper.dbModelToDomain(it) } }
    }

    private fun getCurrentWeatherFromDb(): Flowable<CurrentWeatherModel> {
        return weatherDao.getCurrentWeather()
                .map {
                    Timber.i("from db - ${it.title}")
                    currentWeatherMapper.dbModelToDomain(it)
                }
    }

    private fun checkForUpdate(): Completable {
        return if (isTimeToUpdate())
            forceUpdate()
        else Completable.complete()
    }

    @SuppressLint("MissingPermission")
    private fun getLocation(): Single<Int> {
        return if (!checkLocationPermissions())
            Single.error(MissingPermissionException())
        else if (!isGpsEnabled())
            Single.error(GpsNotEnabledException())
        else
            rxLocation.lastKnownLocation
                    .singleOrError()
                    .flatMap { weatherApi.getLocation("${it.latitude},${it.longitude}").subscribeOn(Schedulers.io()) }
                    .map { it[0].woeid }
    }

    private fun checkLocationPermissions(): Boolean {
        return Build.VERSION.SDK_INT < 23 ||
                ActivityCompat.checkSelfPermission(
                        context, Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun isGpsEnabled(): Boolean {
        val manager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    private fun isTimeToUpdate() =
            System.currentTimeMillis() - sharedPrefsRepository.getLastUpdatedTime() >= ONE_HOUR

}
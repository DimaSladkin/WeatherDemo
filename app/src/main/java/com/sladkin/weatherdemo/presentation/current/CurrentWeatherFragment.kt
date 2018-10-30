package com.sladkin.weatherdemo.presentation.current

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sladkin.weatherdemo.R
import com.sladkin.weatherdemo.di.CURRENT_SCOPE
import com.sladkin.weatherdemo.domain.entity.CurrentWeatherModel
import com.sladkin.weatherdemo.domain.entity.WeatherModel
import com.sladkin.weatherdemo.extention.getHour
import com.sladkin.weatherdemo.extention.getResourceForString
import com.sladkin.weatherdemo.extention.parseIsoToDate
import kotlinx.android.synthetic.main.additional_info_view.*
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.android.synthetic.main.current_weather_view.*
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.ext.android.bindScope
import org.koin.androidx.scope.ext.android.getOrCreateScope

class CurrentWeatherFragment : Fragment(), CurrentWeatherPresenter.CurrentWeatherView {

    interface CurrentWeatherFragmentListener {
        fun onError(e: Throwable)
    }

    val listener by lazy { activity as CurrentWeatherFragmentListener }

    val presenter: CurrentWeatherPresenter<CurrentWeatherPresenter.CurrentWeatherView> by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindScope(getOrCreateScope(CURRENT_SCOPE))
        presenter.setView(this)
        presenter.onViewCreated()

        swipeRefreshLayout.setOnRefreshListener { presenter.onSwipeToRefresh() }
    }

    override fun setCurrentWeather(currentWeatherModel: CurrentWeatherModel) {
        cityTv.text = currentWeatherModel.title
        tempTv.text = context?.getString(R.string.temp, currentWeatherModel.weather?.theTemp)
        weatherImage.setImageResource(getResourceForString(currentWeatherModel.weather?.stateAbr))
        statusTv.text = currentWeatherModel.weather?.state
        maxTempTv.text = context?.getString(R.string.temp, currentWeatherModel.weather?.maxTemp)
        minTempTv.text = context?.getString(R.string.temp, currentWeatherModel.weather?.minTemp)
        sunsetTimeTv.text = context?.getString(R.string.sunset_time,
                currentWeatherModel.sunrise?.parseIsoToDate()?.getHour(),
                currentWeatherModel.sunset?.parseIsoToDate()?.getHour())
        setAdditionalData(currentWeatherModel.weather)
    }

    private fun setAdditionalData(weatherModel: WeatherModel?) {
        humidityValue.text = context?.getString(R.string.percents, weatherModel?.humidity?.toInt())
        cloudValue.text = context?.getString(R.string.percents, weatherModel?.humidity?.toInt())
        visibilityValue.text = context?.getString(R.string.km, weatherModel?.visibility)
        dropValue.text = context?.getString(R.string.percents, weatherModel?.humidity?.toInt())
        pressureValue.text = context?.getString(R.string.mb, weatherModel?.airPresure)
        windStatus.text = weatherModel?.compasPoint
        windValue.text = context?.getString(R.string.km_per_hour, weatherModel?.windSpeed)
    }

    override fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun onError(throwable: Throwable) {
        listener.onError(throwable)
        hideLoading()
    }

    override fun onPause() {
        super.onPause()
        presenter.destroy()
        hideLoading()
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
    }

    override fun onDetach() {
        super.onDetach()
        presenter.destroy()
    }
}
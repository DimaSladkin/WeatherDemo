package com.sladkin.weatherdemo.presentation.current

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sladkin.weatherdemo.R
import com.sladkin.weatherdemo.di.CURRENT_SCOPE
import com.sladkin.weatherdemo.domain.entity.CurrentWeatherModel
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.ext.android.bindScope
import org.koin.androidx.scope.ext.android.getOrCreateScope
import timber.log.Timber

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
    }

    override fun setCurrentWeather(currentWeatherModel: CurrentWeatherModel) {
        Timber.i(currentWeatherModel.title)
    }

    override fun onError(throwable: Throwable) {
        listener.onError(throwable)
    }

    override fun onPause() {
        super.onPause()
        presenter.destroy()
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
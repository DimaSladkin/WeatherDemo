package com.sladkin.weatherdemo.presentation.daily

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sladkin.weatherdemo.R
import com.sladkin.weatherdemo.di.DAILY_SCOPE
import com.sladkin.weatherdemo.domain.entity.WeatherModel
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.ext.android.bindScope
import org.koin.androidx.scope.ext.android.getOrCreateScope

class DailyFragment : Fragment(), DailyPresenter.DailyView {

    interface DailyFragmentListener {
        fun onError(e: Throwable)
    }

    private val listener by lazy { activity as DailyFragmentListener }

    private val presenter: DailyPresenter<DailyPresenter.DailyView> by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.daily_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindScope(getOrCreateScope(DAILY_SCOPE))
        presenter.setView(this)
        presenter.onViewCreated()
    }

    override fun onError(e: Throwable) {
        listener.onError(e)
    }

    override fun showDailyList(list: List<WeatherModel>) {

    }
}
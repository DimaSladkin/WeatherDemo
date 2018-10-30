package com.sladkin.weatherdemo.presentation

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sladkin.weatherdemo.R
import com.sladkin.weatherdemo.presentation.adapter.ViewPagerAdapter
import com.sladkin.weatherdemo.presentation.current.CurrentWeatherFragment
import com.sladkin.weatherdemo.presentation.daily.DailyFragment
import com.sladkin.weatherdemo.presentation.hourly.HourlyFragment
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.main_activity.*
import timber.log.Timber

class MainActivity : AppCompatActivity(),
        CurrentWeatherFragment.CurrentWeatherFragmentListener,
        DailyFragment.DailyFragmentListener,
        HourlyFragment.HourlyFragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        checkPermission()
    }

    private fun setUpViewPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(CurrentWeatherFragment(), "Current")
        adapter.addFragment(DailyFragment(), "Daily")
        adapter.addFragment(HourlyFragment(), "Hourly")
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun checkPermission() {
        RxPermissions(this)
                .request(Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(
                        {
                            setUpViewPager()
                        },
                        {
                            Timber.i("No permission!!")
                        }
                )
    }

    override fun onError(e: Throwable) {

    }
}
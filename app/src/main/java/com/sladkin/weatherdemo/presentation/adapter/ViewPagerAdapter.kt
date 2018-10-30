package com.sladkin.weatherdemo.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private val fragmentList = ArrayList<Pair<Fragment, String>>()

    override fun getItem(position: Int): Fragment {
        return fragmentList[position].first
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(Pair(fragment, title))
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentList[position].second
    }
}
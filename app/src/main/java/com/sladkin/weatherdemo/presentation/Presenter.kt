package com.sladkin.weatherdemo.presentation

interface Presenter<T> {

    fun setView(view: T)

    fun destroy()
}
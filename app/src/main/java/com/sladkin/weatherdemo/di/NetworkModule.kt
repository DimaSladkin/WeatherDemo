package com.sladkin.weatherdemo.di

import com.google.gson.GsonBuilder
import com.sladkin.weatherdemo.BuildConfig
import com.sladkin.weatherdemo.data.network.api.WeatherApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val REQUEST_TIMEOUT = 30L
private const val BASE_URL = "https://www.metaweather.com"

val networkModule = module {
    single { retrofit(get(), get(), get()) }
    single { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) }
    single<Converter.Factory> { GsonConverterFactory.create(get()) }
    single<CallAdapter.Factory> { RxJava2CallAdapterFactory.create() }
    single {
        GsonBuilder()
                .setLenient()
                .create()
    }
    single {
        OkHttpClient.Builder()
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .apply { if (BuildConfig.DEBUG) addInterceptor(get<HttpLoggingInterceptor>()) }
                .build()
    }

    single { getApi<WeatherApi>(get()) }
}

private fun retrofit(
        client: OkHttpClient,
        callAdapterFactory: CallAdapter.Factory,
        converterFactory: Converter.Factory
): Retrofit {
    return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(callAdapterFactory)
            .addConverterFactory(converterFactory)
            .build()
}

private inline fun <reified T> getApi(retrofit: Retrofit): T =
        retrofit.create(T::class.java)
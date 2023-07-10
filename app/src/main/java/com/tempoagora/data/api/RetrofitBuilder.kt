package com.tempoagora.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal object RetrofitBuilder {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.weatherbit.io/v2.0/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getCurrentWeather(): WeatherBitService {
        return retrofit.create(WeatherBitService::class.java)
    }
}
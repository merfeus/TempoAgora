package com.tempoagora.domain.repository

import com.tempoagora.domain.entity.WeatherComponent
import com.tempoagora.domain.entity.WeatherForecastComponent
import kotlinx.coroutines.flow.Flow


internal interface WeatherRepository {

    fun getWeather(latitude: Double, longitude: Double, include: String): Flow<WeatherComponent>
    fun getWeatherForecast(latitude: Double, longitude: Double): Flow<WeatherForecastComponent>
}
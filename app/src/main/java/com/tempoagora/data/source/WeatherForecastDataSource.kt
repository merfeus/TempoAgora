package com.tempoagora.data.source

import com.tempoagora.domain.entity.WeatherForecastComponent
import kotlinx.coroutines.flow.Flow

internal interface WeatherForecastDataSource {
    fun getWeatherForecast(
        latitude: Double,
        longitude: Double,
    ): Flow<WeatherForecastComponent>
}
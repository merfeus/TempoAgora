package com.tempoagora.data.source

import com.tempoagora.domain.entity.WeatherComponent
import kotlinx.coroutines.flow.Flow

internal interface WeatherDataSource {
    fun getWeather(latitude: Double, longitude: Double, include: String): Flow<WeatherComponent>
}
package com.tempoagora.domain.repository

import com.tempoagora.domain.entity.WeatherComponent
import kotlinx.coroutines.flow.Flow


internal interface WeatherRepository {

    fun getWeather(latitude: Double, longitude: Double, include: String) : Flow<WeatherComponent>
}
package com.tempoagora.domain.usecase

import com.tempoagora.domain.entity.WeatherComponent
import com.tempoagora.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

internal class GetWeatherUseCase(
    private val repository: WeatherRepository
) {
    operator fun invoke(
        latitude: Double,
        longitude: Double,
        include: String
    ): Flow<WeatherComponent> =
        repository.getWeather(latitude, longitude, include)

}
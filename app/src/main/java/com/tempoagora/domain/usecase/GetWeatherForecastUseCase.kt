package com.tempoagora.domain.usecase

import com.tempoagora.domain.entity.WeatherForecastComponent
import com.tempoagora.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

internal class GetWeatherForecastUseCase(
    private val repository: WeatherRepository
) {
    operator fun invoke(
        latitude: Double,
        longitude: Double
    ): Flow<WeatherForecastComponent> = repository.getWeatherForecast(latitude, longitude)

}
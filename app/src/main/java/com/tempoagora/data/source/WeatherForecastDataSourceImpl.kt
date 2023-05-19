package com.tempoagora.data.source

import com.tempoagora.data.api.WeatherBitService
import com.tempoagora.data.mapper.WeatherForecastMapper
import com.tempoagora.domain.entity.WeatherForecastComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class WeatherForecastDataSourceImpl(
    private val weatherBitService: WeatherBitService,
    private val mapper: WeatherForecastMapper
) : WeatherForecastDataSource {

    override fun getWeatherForecast(
        latitude: Double,
        longitude: Double,
    ): Flow<WeatherForecastComponent> = flow {
        emit(
            mapper.map(
                weatherBitService.getWeatherForecast(
                    latitude = latitude,
                    longitude = longitude,
                )
            )
        )
    }

}
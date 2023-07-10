package com.tempoagora.data.source

import com.tempoagora.data.api.WeatherBitService
import com.tempoagora.data.mapper.WeatherMapper
import com.tempoagora.domain.entity.WeatherComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class WeatherDataSourceImpl(
    val weatherBitService: WeatherBitService,
    val mapper: WeatherMapper
) : WeatherDataSource {
    override fun getWeather(
        latitude: Double,
        longitude: Double,
        include: String
    ): Flow<WeatherComponent> = flow {
        emit(
            mapper.map(
                weatherBitService.getWeather(
                    latitude = latitude,
                    longitude = longitude,
                    include = include
                )
            )
        )
    }
}
package com.tempoagora.data.repository

import com.tempoagora.data.source.WeatherDataSource
import com.tempoagora.data.source.WeatherForecastDataSource
import com.tempoagora.domain.entity.WeatherComponent
import com.tempoagora.domain.entity.WeatherForecastComponent
import com.tempoagora.domain.repository.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow


internal class WeatherRepositoryImpl(
    private val dataSource: WeatherDataSource,
    private val dataSourceForecast: WeatherForecastDataSource
) : WeatherRepository {
    override fun getWeather(
        latitude: Double,
        longitude: Double,
        include: String
    ): Flow<WeatherComponent> {
        return dataSource.getWeather(latitude, longitude, include)
    }

    override fun getWeatherForecast(
        latitude: Double,
        longitude: Double,
    ): Flow<WeatherForecastComponent> {
        return dataSourceForecast.getWeatherForecast(latitude, longitude)
    }
}
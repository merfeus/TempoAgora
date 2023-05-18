package com.tempoagora.data.repository

import com.tempoagora.data.source.WeatherDataSource
import com.tempoagora.domain.entity.WeatherComponent
import com.tempoagora.domain.repository.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow


internal class WeatherRepositoryImpl(
    private val dataSource: WeatherDataSource,
) : WeatherRepository {
    override fun getWeather(latitude: Double, longitude: Double, include: String) : Flow<WeatherComponent> {
       return dataSource.getWeather(latitude, longitude, include)
    }
}
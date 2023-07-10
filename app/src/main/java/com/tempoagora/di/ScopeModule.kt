package com.tempoagora.di


import com.tempoagora.data.api.RetrofitBuilder
import com.tempoagora.data.mapper.WeatherForecastMapper
import com.tempoagora.data.mapper.WeatherMapper
import com.tempoagora.data.repository.WeatherRepositoryImpl
import com.tempoagora.data.source.WeatherDataSourceImpl
import com.tempoagora.data.source.WeatherForecastDataSourceImpl
import com.tempoagora.domain.repository.WeatherRepository
import com.tempoagora.domain.usecase.GetWeatherForecastUseCase
import com.tempoagora.domain.usecase.GetWeatherUseCase
import com.tempoagora.presentation.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        WeatherViewModel(
            useCase = GetWeatherUseCase(
                repository = get()
            ),
            useCaseForecast = GetWeatherForecastUseCase(
                repository = get()
            )
        )
    }
}
val repositoryModule = module {
    single<WeatherRepository> {
        WeatherRepositoryImpl(
            dataSource = WeatherDataSourceImpl(
                weatherBitService = RetrofitBuilder.getCurrentWeather(),
                mapper = WeatherMapper()
            ),
            dataSourceForecast = WeatherForecastDataSourceImpl(
                weatherBitService = RetrofitBuilder.getCurrentWeather(),
                mapper = WeatherForecastMapper()
            )
        )
    }
}
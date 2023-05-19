package com.tempoagora.presentation.viewmodel

import com.tempoagora.domain.entity.DataForecastComponent
import com.tempoagora.domain.entity.WeatherComponent

internal sealed class MainViewAction {
    object NavigateToInsert : MainViewAction()
    data class Weather(val list: WeatherComponent) : MainViewAction()
    data class LoadingState(val isLoading: Boolean) : MainViewAction()
    data class ErrorScreen(val errorScreen: Boolean) : MainViewAction()
    object DeniedPermission : MainViewAction()
    data class WeatherForecast(val list: List<DataForecastComponent>) : MainViewAction()
}
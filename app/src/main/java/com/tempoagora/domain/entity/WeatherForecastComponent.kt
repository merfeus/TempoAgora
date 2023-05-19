package com.tempoagora.domain.entity

import com.google.gson.annotations.SerializedName
import com.tempoagora.data.model.DataForecastResponse

internal data class WeatherForecastComponent(
    val cityName: String,
    val countryCode: String,
    val data: List<DataForecastComponent>,
    val lat: String,
    val lon: String,
    val stateCode: String,
    val timezone: String
)
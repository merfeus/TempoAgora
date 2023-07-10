package com.tempoagora.domain.entity

import com.google.gson.annotations.SerializedName

internal data class WeatherLocalForecastComponent(
    val code: Int,
    val description: String,
    val icon: String
)

package com.tempoagora.data.model

import com.google.gson.annotations.SerializedName

internal data class WeatherForecastResponse(
    @SerializedName("city_name") val cityName: String,
    @SerializedName("country_code") val countryCode: String,
    @SerializedName("data") val data: List<DataForecastResponse>,
    @SerializedName("lat") val lat: String,
    @SerializedName("lon") val lon: String,
    @SerializedName("state_code") val stateCode: String,
    @SerializedName("timezone") val timezone: String
)
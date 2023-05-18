package com.tempoagora.data.model

import com.google.gson.annotations.SerializedName

internal data class WeatherLocalResponse(
    @SerializedName("code") val code: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
)
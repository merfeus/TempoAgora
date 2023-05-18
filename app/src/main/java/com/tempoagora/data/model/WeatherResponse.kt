package com.tempoagora.data.model

import com.google.gson.annotations.SerializedName

internal data class WeatherResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("data")
    val data: List<DataResponse>,
    @SerializedName("minutely")
    val minutely: List<String>
)
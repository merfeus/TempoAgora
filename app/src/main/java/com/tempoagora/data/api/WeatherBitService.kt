package com.tempoagora.data.api

import com.tempoagora.data.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface WeatherBitService {

    @GET("current")
    suspend fun getWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("key") apiKey: String = "",
        @Query("include") include: String
    ): WeatherResponse

}
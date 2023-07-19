package com.tempoagora.data.api

import com.tempoagora.BuildConfig
import com.tempoagora.data.model.WeatherForecastResponse
import com.tempoagora.data.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface WeatherBitService {

    @GET("current")
    suspend fun getWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("key") apiKey: String = "aa23877c90f7432c99de475a04db0ff8",
        @Query("include") include: String,
        @Query("lang") lang: String = "pt"
    ): WeatherResponse

    @GET("forecast/daily")
    suspend fun getWeatherForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("key") apiKey: String = "aa23877c90f7432c99de475a04db0ff8",
        @Query("lang") lang: String = "pt"
    ): WeatherForecastResponse
}
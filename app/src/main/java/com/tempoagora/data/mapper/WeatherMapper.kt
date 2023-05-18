package com.tempoagora.data.mapper

import com.tempoagora.data.model.extensions.Mapper
import com.tempoagora.data.model.DataResponse
import com.tempoagora.data.model.WeatherLocalResponse
import com.tempoagora.data.model.WeatherResponse
import com.tempoagora.domain.entity.DataComponent
import com.tempoagora.domain.entity.WeatherComponent
import com.tempoagora.domain.entity.WeatherLocalComponent

internal class WeatherMapper : Mapper<WeatherResponse, WeatherComponent> {
    override fun map(source: WeatherResponse): WeatherComponent = with(source) {
        return WeatherComponent(
            count = count,
            data = data.map {
                mapWeather(it)
            }
        )
    }

    private fun mapWeather(it: DataResponse): DataComponent = with(it) {
        DataComponent(
            appTemp = appTemp,
            aqi = aqi,
            cityName = cityName,
            clouds = clouds,
            countryCode = countryCode,
            datetime = datetime,
            dewpt = dewpt,
            dhi = dhi,
            dni = dni,
            elevAngle = elevAngle,
            ghi = ghi,
            gust = gust,
            hAngle = hAngle,
            lat = lat,
            lon = lon,
            obTime = obTime,
            pod = pod,
            precip = precip,
            pres = pres,
            rh = rh,
            slp = slp,
            solarRad = solarRad,
            sources = sources,
            stateCode = stateCode,
            station = station,
            sunrise = sunrise,
            sunset = sunset,
            temp = temp,
            timezone = timezone,
            ts = ts,
            uv = uv,
            vis = vis,
            weather = mapWeatherLocal(weather),
            windCdir = windCdir,
            windCdirFull = windCdirFull,
            windDir = windDir,
            windSpd = windSpd
        )
    }

    private fun mapWeatherLocal(weather: WeatherLocalResponse): WeatherLocalComponent =
        with(weather) {
            WeatherLocalComponent(
                code = code,
                description = description,
                icon = icon
            )
        }
}
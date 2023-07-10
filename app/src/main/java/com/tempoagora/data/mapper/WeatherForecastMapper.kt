package com.tempoagora.data.mapper

import com.tempoagora.data.model.DataForecastResponse
import com.tempoagora.data.model.WeatherForecastResponse
import com.tempoagora.data.model.WeatherLocalForecastResponse
import com.tempoagora.data.mapper.extensions.Mapper
import com.tempoagora.domain.entity.DataForecastComponent
import com.tempoagora.domain.entity.WeatherForecastComponent
import com.tempoagora.domain.entity.WeatherLocalForecastComponent

internal class WeatherForecastMapper : Mapper<WeatherForecastResponse, WeatherForecastComponent> {
    override fun map(source: WeatherForecastResponse): WeatherForecastComponent = with(source) {
        return WeatherForecastComponent(
            cityName = cityName,
            countryCode = countryCode,
            data = data.map {
                mapWeather(it)
            },
            lat = lat,
            lon = lon,
            stateCode = stateCode,
            timezone = timezone
        )

    }

    private fun mapWeather(it: DataForecastResponse): DataForecastComponent = with(it) {
        return DataForecastComponent(
            appMaxTemp = appMaxTemp,
            appMinTemp = appMinTemp,
            clouds = clouds,
            cloudsHi = cloudsHi,
            cloudsLow = cloudsLow,
            cloudsMid = cloudsMid,
            datetime = datetime,
            dewpt = dewpt,
            highTemp = highTemp,
            lowTemp = lowTemp,
            maxDhi = maxDhi,
            maxTemp = maxTemp,
            minTemp = minTemp,
            moonPhase = moonPhase,
            moonPhaseLunation = moonPhaseLunation,
            moonriseTs = moonriseTs,
            moonsetTs = moonsetTs,
            ozone = ozone,
            pop = pop,
            precip = precip,
            pres = pres,
            rh = rh,
            slp = slp,
            snow = snow,
            snowDepth = snowDepth,
            sunriseTs = sunriseTs,
            sunsetTs = sunsetTs,
            temp = temp,
            ts = ts,
            uv = uv,
            validDate = validDate,
            vis = vis,
            weather = mapWeatherLocal(weather),
            windCdir = windCdir,
            windCdirFull = windCdirFull,
            windDir = windDir,
            windGustSpd = windGustSpd,
            windSpd = windSpd
        )
    }

    private fun mapWeatherLocal(weather: WeatherLocalForecastResponse): WeatherLocalForecastComponent =
        with(weather) {
            WeatherLocalForecastComponent(
                code = code,
                description = description,
                icon = icon
            )
        }
}
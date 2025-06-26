package com.example.weatherforecastapp.domain.mapper

import com.example.weatherforecastapp.data.model.CurrentWeatherResponse
import com.example.weatherforecastapp.domain.model.CurrentWeatherData

fun CurrentWeatherResponse.toCurrentWeatherData(): CurrentWeatherData {
    return CurrentWeatherData(
        coord = CurrentWeatherData.Coord(
            lon = this.coord?.lon,
            lat = this.coord?.lat
        ),
        weather = this.weather?.map {
            CurrentWeatherData.Weather(
                id = it.id,
                main = it.main,
                description = it.description,
                icon = it.icon
            )
        },
        base = this.base,
        main = CurrentWeatherData.Main(
            temp = this.main?.temp,
            feelsLike = this.main?.feelsLike,
            tempMin = this.main?.tempMin,
            tempMax = this.main?.tempMax,
            pressure = this.main?.pressure,
            humidity = this.main?.humidity,
            seaLevel = this.main?.seaLevel,
            grndLevel = this.main?.grndLevel
        ),
        visibility = this.visibility,
        wind = CurrentWeatherData.Wind(
            speed = this.wind?.speed,
            deg = this.wind?.deg,
            gust = this.wind?.gust
        ),
        rain = this.rain?.let {
            CurrentWeatherData.Rain(
                oneHour = it.oneHour
            )
        },
        clouds = CurrentWeatherData.Clouds(
            all = this.clouds?.all
        ),
        dt = this.dt,
        sys = CurrentWeatherData.Sys(
            type = this.sys?.type,
            id = this.sys?.id,
            country = this.sys?.country,
            sunrise = this.sys?.sunrise,
            sunset = this.sys?.sunset
        ),
        timezone = this.timezone,
        id = this.id,
        name = this.name,
        cod = this.cod
    )
}


package com.example.weatherforecastapp.domain.model

data class CurrentWeatherData(
    val coord: Coord? = null,
    val weather: List<Weather>? = null,
    val base: String? = null,
    val main: Main? = null,
    val visibility: Int? = null,
    val wind: Wind? = null,
    val rain: Rain? = null,
    val clouds: Clouds? = null,
    val dt: Long? = null,
    val sys: Sys? = null,
    val timezone: Int? = null,
    val id: Int? = null,
    val name: String? = null,
    val cod: Int? = null
) {
    data class Coord(
        val lon: Double? = null,
        val lat: Double? = null
    )

    data class Weather(
        val id: Int? = null,
        val main: String? = null,
        val description: String? = null,
        val icon: String? = null
    )

    data class Main(
        val temp: Double? = null,
        val feelsLike: Double? = null,
        val tempMin: Double? = null,
        val tempMax: Double? = null,
        val pressure: Int? = null,
        val humidity: Int? = null,
        val seaLevel: Int? = null,
        val grndLevel: Int? = null
    )

    data class Wind(
        val speed: Double? = null,
        val deg: Int? = null,
        val gust: Double? = null
    )

    data class Rain(
        val oneHour: Double? = null
    )

    data class Clouds(
        val all: Int? = null
    )

    data class Sys(
        val type: Int? = null,
        val id: Int? = null,
        val country: String? = null,
        val sunrise: Long? = null,
        val sunset: Long? = null
    )
}

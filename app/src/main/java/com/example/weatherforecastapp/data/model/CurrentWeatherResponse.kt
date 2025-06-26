package com.example.weatherforecastapp.data.model

import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
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
        @SerializedName("feels_like")
        val feelsLike: Double? = null,
        @SerializedName("temp_min")
        val tempMin: Double? = null,
        @SerializedName("temp_max")
        val tempMax: Double? = null,
        val pressure: Int? = null,
        val humidity: Int? = null,
        @SerializedName("sea_level")
        val seaLevel: Int? = null,
        @SerializedName("grnd_level")
        val grndLevel: Int? = null
    )

    data class Wind(
        val speed: Double? = null,
        val deg: Int? = null,
        val gust: Double? = null
    )

    data class Rain(
        @SerializedName("1h")
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

package com.example.weatherforecastapp.domain.repository

import com.example.weatherforecastapp.data.model.CurrentWeatherResponse

interface Repository {

    suspend fun getCurrentWeatherAPI(
        lat: String,
        lon: String,
        apiKey: String
    ): CurrentWeatherResponse

}
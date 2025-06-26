package com.example.weatherforecastapp.domain.repository

import com.example.weatherforecastapp.domain.model.CurrentWeatherData

interface Repository {

    suspend fun getCurrentWeatherAPI(
        lat: String,
        lon: String
    ): CurrentWeatherData

}
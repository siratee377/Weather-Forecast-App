package com.example.weatherforecastapp.data.repository

import com.example.weatherforecastapp.data.model.CurrentWeatherResponse
import com.example.weatherforecastapp.data.network.ApiService
import com.example.weatherforecastapp.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: ApiService) : Repository {

    override suspend fun getCurrentWeatherAPI(
        lat: String,
        lon: String,
        apiKey: String
    ): CurrentWeatherResponse {
        return apiService.getCurrentWeatherAPI(lat = lat, lon = lon, apiKey = apiKey)
    }
}
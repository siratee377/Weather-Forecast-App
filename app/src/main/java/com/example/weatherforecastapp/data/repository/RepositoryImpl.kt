package com.example.weatherforecastapp.data.repository

import com.example.weatherforecastapp.data.network.ApiService
import com.example.weatherforecastapp.domain.mapper.toCurrentWeatherData
import com.example.weatherforecastapp.domain.model.CurrentWeatherData
import com.example.weatherforecastapp.domain.repository.Repository
import com.example.weatherforecastapp.utils.Constant.API_KEY
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: ApiService) : Repository {

    override suspend fun getCurrentWeatherAPI(
        lat: String,
        lon: String
    ): CurrentWeatherData {
        return apiService.getCurrentWeatherAPI(
            lat = lat,
            lon = lon,
            apiKey = API_KEY
        ).toCurrentWeatherData()
    }
}
package com.example.weatherforecastapp.data.repository

import com.example.weatherforecastapp.data.network.ApiService
import com.example.weatherforecastapp.domain.mapper.toCurrentWeatherData
import com.example.weatherforecastapp.domain.model.CurrentWeatherData
import com.example.weatherforecastapp.domain.repository.Repository
import com.example.weatherforecastapp.utils.Constant.API_KEY
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: ApiService) : Repository {

    override suspend fun getCurrentWeatherAPI(
        cityName: String
    ): CurrentWeatherData {
        return apiService.getCurrentWeatherAPI(
            cityName = cityName,
            apiKey = API_KEY
        ).toCurrentWeatherData()
    }
}
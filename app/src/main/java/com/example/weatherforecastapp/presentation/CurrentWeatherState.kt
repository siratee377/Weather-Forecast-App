package com.example.weatherforecastapp.presentation

import com.example.weatherforecastapp.domain.model.CurrentWeatherData

sealed interface CurrentWeatherState {
    object Idle : CurrentWeatherState
    object Loading : CurrentWeatherState
    data class Error(val message: String) : CurrentWeatherState
    data class Success(val data: CurrentWeatherData?) : CurrentWeatherState
}
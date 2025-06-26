package com.example.weatherforecastapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecastapp.core.common.UiState
import com.example.weatherforecastapp.domain.usecase.GetCurrentWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
) : ViewModel() {

    private val _city = MutableStateFlow("")
    val city: StateFlow<String> = _city

    private val _uiState = MutableStateFlow<CurrentWeatherState>(CurrentWeatherState.Idle)
    val uiState: StateFlow<CurrentWeatherState> = _uiState

    fun onCityChange(new: String) {
        _city.value = new
    }

    fun fetchWeather() {
        val query = _city.value.trim()

        if (query.isEmpty()) {
            _uiState.value = CurrentWeatherState.Error("Please enter a city name")
            return
        }

        getCurrentWeatherUseCase.invoke(cityName = query).map {
            when (it) {
                is UiState.Loading -> {
                    _uiState.value = CurrentWeatherState.Loading
                }

                is UiState.Success -> {
                    _uiState.value = CurrentWeatherState.Success(it.data)
                }

                is UiState.Error -> {
                    _uiState.value = CurrentWeatherState.Error("Failed: ${it.message.orEmpty()}")
                }
            }
        }.launchIn(viewModelScope)
    }

}
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

    private val _currentWeatherState = MutableStateFlow<CurrentWeatherState?>(null)
    val currentWeatherState: StateFlow<CurrentWeatherState?>
        get() = _currentWeatherState

    fun getProductDetailAPi(lat: String, long: String) {

        getCurrentWeatherUseCase.invoke(lat = lat, long = long).map {
            when (it) {
                is UiState.Loading -> {
                    _currentWeatherState.value = CurrentWeatherState.Loading
                }

                is UiState.Success -> {
                    _currentWeatherState.value = CurrentWeatherState.Success(it.data)
                }

                is UiState.Error -> {
                    _currentWeatherState.value = CurrentWeatherState.Error(it.message.orEmpty())
                }
            }
        }.launchIn(viewModelScope)
    }

}
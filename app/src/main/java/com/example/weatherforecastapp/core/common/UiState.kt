package com.example.weatherforecastapp.core.common

sealed interface UiState {
    data object Loading : UiState
    data class Error(val throwable: Throwable) : UiState
    data class Success(val data: List<String>) : UiState
}
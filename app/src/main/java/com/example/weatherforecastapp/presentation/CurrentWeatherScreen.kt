package com.example.weatherforecastapp.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CurrentWeatherScreen(
    viewModel: CurrentWeatherViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val city by viewModel.city.collectAsState()

    WeatherMainContent(
        isTablet = isTablet(),
        city = city,
        onCityChange = viewModel::onCityChange,
        onFetchWeather = viewModel::fetchWeather,
        uiState = uiState,
    )
}

@Composable
private fun WeatherMainContent(
    isTablet: Boolean,
    city: String,
    onCityChange: (String) -> Unit,
    onFetchWeather: () -> Unit,
    uiState: CurrentWeatherState,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { WeatherTopBar() }
    ) { paddingValues ->
        if (isTablet) {
            TabletMainContent(paddingValues, city, onCityChange, onFetchWeather, uiState)
        } else {
            PhoneMainContent(paddingValues, city, onCityChange, onFetchWeather, uiState)
        }
    }
}

@Composable
private fun PhoneMainContent(
    paddingValues: PaddingValues,
    city: String,
    onCityChange: (String) -> Unit,
    onFetchWeather: () -> Unit,
    uiState: CurrentWeatherState
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        WeatherInput(
            modifier = Modifier.fillMaxWidth(),
            city = city,
            onCityChange = onCityChange,
            onFetchWeather = onFetchWeather
        )

        WeatherDisplay(
            modifier = Modifier.fillMaxWidth(),
            uiState = uiState,
        )
    }
}

@Composable
private fun TabletMainContent(
    paddingValues: PaddingValues,
    city: String,
    onCityChange: (String) -> Unit,
    onFetchWeather: () -> Unit,
    uiState: CurrentWeatherState
) {
    Row(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        WeatherInput(
            modifier = Modifier.weight(1f),
            city = city,
            onCityChange = onCityChange,
            onFetchWeather = onFetchWeather
        )

        WeatherDisplay(
            modifier = Modifier.weight(1f),
            uiState = uiState
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WeatherTopBar() {
    TopAppBar(
        title = { Text("Weather App") }
    )
}

@Composable
private fun WeatherInput(
    city: String,
    onCityChange: (String) -> Unit,
    onFetchWeather: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = city,
            onValueChange = onCityChange,
            label = {
                Text("City name")
            },
            singleLine = true
        )

        Spacer(Modifier.height(8.dp))

        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = onFetchWeather,
        ) {
            Text("Get Weather")
        }
    }
}

@Composable
private fun WeatherDisplay(
    uiState: CurrentWeatherState,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        when (uiState) {
            is CurrentWeatherState.Idle -> {
                Text("Enter a city and push \"Get Weather\"")
            }

            is CurrentWeatherState.Loading -> {
                CircularProgressIndicator()
            }

            is CurrentWeatherState.Success -> {
                uiState.data?.let { data ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            "Temperature: ${data.main?.temp}°C",
                            style = MaterialTheme.typography.headlineSmall
                        )

                        Text(
                            "Description: ${data.weather?.firstOrNull()?.description.orEmpty()}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }

            is CurrentWeatherState.Error -> {
                Text(
                    text = uiState.message,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
private fun isTablet() = LocalConfiguration.current.screenWidthDp >= 600
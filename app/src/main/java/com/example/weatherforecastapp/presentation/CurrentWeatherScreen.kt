package com.example.weatherforecastapp.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentWeatherScreen(
    viewModel: CurrentWeatherViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val city by viewModel.city.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text("Weather App") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            if (isTablet()) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    InputSection(
                        city = city,
                        onCityChange = viewModel::onCityChange,
                        onFetchWeather = viewModel::fetchWeather,
                        modifier = Modifier.weight(1f)
                    )
                    DisplaySection(uiState, modifier = Modifier.weight(1f))
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    InputSection(
                        city = city,
                        onCityChange = viewModel::onCityChange,
                        onFetchWeather = viewModel::fetchWeather,
                        modifier = Modifier.fillMaxWidth()
                    )
                    DisplaySection(uiState, modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}


@Composable
fun InputSection(
    city: String,
    onCityChange: (String) -> Unit,
    onFetchWeather: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = city,
            onValueChange = onCityChange,
            label = { Text("City name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        Button(
            onClick = onFetchWeather,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Get Weather")
        }
    }
}

@Composable
fun DisplaySection(uiState: CurrentWeatherState, modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        when (uiState) {
            is CurrentWeatherState.Idle -> Text("Enter a city and push \"Get Weather\"")
            is CurrentWeatherState.Loading -> CircularProgressIndicator()
            is CurrentWeatherState.Success -> {
                val w = uiState.data
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "Temperature: ${w?.main?.temp}°C",
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Text(
                        "Description: ${w?.weather?.first()?.description}",
                        style = MaterialTheme.typography.bodyLarge
                    )
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
fun isTablet(): Boolean {
    return LocalConfiguration.current.screenWidthDp >= 600
}
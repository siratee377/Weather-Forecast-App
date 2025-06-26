package com.example.weatherforecastapp.data.network

import com.example.weatherforecastapp.data.model.CurrentWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("weather")
    suspend fun getCurrentWeatherAPI(
        @Query("city name") cityName: String,
        @Query("API key") apiKey: String
    ): CurrentWeatherResponse

}
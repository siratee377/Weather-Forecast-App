package com.example.weatherforecastapp.data.network

import com.example.weatherforecastapp.data.model.CurrentWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("weather")
    suspend fun getCurrentWeatherAPI(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
        @Query("units") unit: String
    ): CurrentWeatherResponse

}
package com.example.weatherforecastapp.data.network

import com.example.weatherforecastapp.data.model.CurrentWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("weather?lat={lat}&lon={lon}&appid={apiKey}")
    suspend fun getCurrentWeatherAPI(
        @Path("lat") lat: String,
        @Path("lon") lon: String,
        @Path("apiKey") apiKey: String
    ): CurrentWeatherResponse

}
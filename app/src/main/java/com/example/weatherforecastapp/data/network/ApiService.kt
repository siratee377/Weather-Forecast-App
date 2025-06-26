package com.example.weatherforecastapp.data.network

import com.example.weatherforecastapp.data.model.CurrentWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("weather?q={city_name}&appid={api_key}&units=metric")
    suspend fun getCurrentWeatherAPI(
        @Path("city_name") cityName: String,
        @Path("api_key") apiKey: String
    ): CurrentWeatherResponse

}
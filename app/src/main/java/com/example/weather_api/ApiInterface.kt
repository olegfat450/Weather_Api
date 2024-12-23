package com.example.weather_api

import com.example.weather_api.models.CurrentWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("weather?")
    suspend fun getCurrentWeather(
        @Query("q")city: String,
        @Query("units")units: String,
        @Query("appid")apiKey: String
    ): Response<CurrentWeather>

    @GET("weather?")
    suspend fun getCurrentWeatherC(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String,
        @Query("appid") apiKey: String
    ): Response<CurrentWeather>

}
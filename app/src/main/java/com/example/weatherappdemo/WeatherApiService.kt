package com.example.weatherappdemo

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.weatherappdemo.WeatherResponse

interface WeatherApiService {
    @GET("weather")
    fun getWeatherByCity(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): Call<WeatherResponse>
}

package com.example.weatherappdemo

data class WeatherResponse(
    val name: String,
    val main: Main,
    val weather: List<Weather>
)

data class Main(val temp: Double)
data class Weather(val main: String, val icon: String)

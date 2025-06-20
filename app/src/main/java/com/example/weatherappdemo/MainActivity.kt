package com.example.weatherappdemo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var weatherAdapter: WeatherAdapter
    private val apiKey = "aa90ff69eb767cb8928a661848cdb3c6"


    private val cities = listOf("New York", "Cairo", "San Diego")
    private val weatherData = mutableListOf<WeatherModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)

        for (city in cities) {
            fetchWeather(city)
        }
    }

    private fun fetchWeather(city: String) {
        RetrofitClient.instance.getWeatherByCity(city, apiKey).enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        val iconUrl = "https://openweathermap.org/img/wn/${body.weather[0].icon}@2x.png"
                        val model = WeatherModel(
                            city = body.name,
                            temperature = "${body.main.temp}°C",
                            status = body.weather[0].main,
                            iconUrl = iconUrl
                        )
                        weatherData.add(model)
                        if (weatherData.size == cities.size) {
                            weatherAdapter = WeatherAdapter(this@MainActivity, weatherData)
                            viewPager.adapter = weatherAdapter
                        }
                    }
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "API Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

package com.example.weatherappdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide




class WeatherAdapter(private val context: Context, private val weatherList: List<WeatherModel>) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    inner class WeatherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cityName: TextView = view.findViewById(R.id.cityName)
        val tempText: TextView = view.findViewById(R.id.tempText)
        val weatherStatus: TextView = view.findViewById(R.id.weatherStatus)
        val weatherIcon: ImageView = view.findViewById(R.id.weatherIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.weather_item, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weather = weatherList[position]
        holder.cityName.text = weather.city
        holder.tempText.text = weather.temperature
        holder.weatherStatus.text = weather.status
        Glide.with(context).load(weather.iconUrl).into(holder.weatherIcon)
    }

    override fun getItemCount(): Int = weatherList.size
}

package com.example.weatherappwithfragments.fragments

class FiveDayWeatherFragment : BaseWeatherFragment() {
    override fun getBaseUrl(): String {
        return "https://dataservice.accuweather.com/forecasts/v1/daily/5day/1710150"
    }
}
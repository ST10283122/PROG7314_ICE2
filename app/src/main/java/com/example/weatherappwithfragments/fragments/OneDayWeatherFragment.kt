package com.example.weatherappwithfragments.fragments

class OneDayWeatherFragment : BaseWeatherFragment() {
    override fun getBaseUrl(): String {
        return "https://dataservice.accuweather.com/forecasts/v1/daily/1day/1710150"
    }
}
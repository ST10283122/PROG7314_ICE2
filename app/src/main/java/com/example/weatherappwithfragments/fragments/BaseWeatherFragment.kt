package com.example.weatherappwithfragments.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.example.DailyForecasts
import com.example.example.ExampleJson2KtKotlin
import com.example.weatherappwithfragments.R
import com.example.weatherappwithfragments.adapters.WeatherAdapter
import com.example.weatherappwithfragments.utils.NetworkUtil
import com.google.gson.Gson
import kotlin.concurrent.thread

abstract class BaseWeatherFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private val gson = Gson()

    // Abstract method to be implemented by subclasses
    abstract fun getBaseUrl(): String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_five_day_weather, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewWeather)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        fetchWeather()
        return view
    }

    private fun fetchWeather() {
        thread {
            val weatherString: String = try {
                val baseUrl = getBaseUrl()
                val networkUtil = NetworkUtil(baseUrl)
                val url = networkUtil.buildURLForWeather()
                if (url != null) {
                    /*
                    Open a connection to the URL.
                    Read the entire content as a plain text string (e.g., HTML, JSON, etc.).
                    Return the result as a String.
                     */
                    url.readText()
                } else {
                    ""
                }
            } catch (e: Exception) {
                e.printStackTrace()
                "Error fetching weather: ${e.message}"
            }

            requireActivity().runOnUiThread {
                var days = emptyList<DailyForecasts>()
                try {
                    Log.e("BaseWeatherFragInRunOnUiThread", "weatherString = $weatherString")
                    val weatherData = gson.fromJson(weatherString, ExampleJson2KtKotlin::class.java)
                    days = weatherData.DailyForecasts
                    recyclerView.adapter = WeatherAdapter(days)
                } catch (e: Exception) {
                    Log.e("BaseWeatherFrag", "Failed to fetch or parse JSON", e)
                }
            }
        }
    }
}
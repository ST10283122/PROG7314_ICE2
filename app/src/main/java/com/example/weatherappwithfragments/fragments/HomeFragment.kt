package com.example.weatherappwithfragments.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.weatherappwithfragments.MainActivity
import com.example.weatherappwithfragments.R

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val btnTodayForecast = view.findViewById<Button>(R.id.btnTodayForecast)
        val btnFiveDayForecast = view.findViewById<Button>(R.id.btnFiveDayForecast)

        btnTodayForecast.setOnClickListener {
            (activity as MainActivity).loadFragment(OneDayWeatherFragment())
        }

        btnFiveDayForecast.setOnClickListener {
            (activity as MainActivity).loadFragment(FiveDayWeatherFragment())
        }

        return view
    }
}
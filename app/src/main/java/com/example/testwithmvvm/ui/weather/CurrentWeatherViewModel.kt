package com.example.testwithmvvm.ui.weather

import androidx.lifecycle.ViewModel
import com.example.testwithmvvm.internal.lazyDeferred
import com.example.testwithmvvm.repostory.Repostory

class CurrentWeatherViewModel (private val currentWeatherrepostory:Repostory): ViewModel() {
    val weather by lazyDeferred {
        currentWeatherrepostory.getCurrentWeather()
    }
}

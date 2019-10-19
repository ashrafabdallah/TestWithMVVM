package com.example.testwithmvvm.repostory

import androidx.lifecycle.LiveData
import com.example.testwithmvvm.data.DataBase.CurrentWeatherEntry

interface Repostory {
    suspend fun getCurrentWeather():LiveData<out CurrentWeatherEntry>

}
package com.example.testwithmvvm.network

import androidx.lifecycle.LiveData
import com.example.testwithmvvm.data.response.CurrentWeatherResponse

interface WeatherNetworkDataSourse {
    val downloadCurrentWeather:LiveData<CurrentWeatherResponse>
    suspend fun featchWeather(location:String)

}
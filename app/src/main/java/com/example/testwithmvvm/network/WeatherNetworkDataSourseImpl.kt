package com.example.testwithmvvm.network

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testwithmvvm.data.response.CurrentWeatherResponse
import com.example.testwithmvvm.internal.NoConnectivityException

class WeatherNetworkDataSourseImpl (private val api:ApiServices,val context:Context ): WeatherNetworkDataSourse {
private val _downloadcurrentWeather=MutableLiveData<CurrentWeatherResponse>()
    override val downloadCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadcurrentWeather

    override suspend fun featchWeather(location: String) {
       try {
           val featchcurrentWeather=api.getCurrentWeather(location).await()
           _downloadcurrentWeather.postValue(featchcurrentWeather)

       }catch (e: NoConnectivityException)
       {
          Log.i("internet","no internet connection ,Please Open this")
          // Toast.makeText(context,"no internet connection ,Please Open this ",Toast.LENGTH_SHORT).show()
       }
    }
}
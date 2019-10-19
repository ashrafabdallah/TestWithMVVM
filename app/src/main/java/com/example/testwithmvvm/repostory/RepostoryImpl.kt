package com.example.testwithmvvm.repostory

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.testwithmvvm.data.DataBase.CurrentWeatherEntry
import com.example.testwithmvvm.data.DataBase.DatabaseDao
import com.example.testwithmvvm.data.response.CurrentWeatherResponse
import com.example.testwithmvvm.network.WeatherNetworkDataSourse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime
import java.util.*

class RepostoryImpl(
    private var dao: DatabaseDao,
    private val weatherdataSource: WeatherNetworkDataSourse
) : Repostory {
    init {
        weatherdataSource.downloadCurrentWeather.observeForever { newcurrentWezather ->
            persistCurrentWeather(newcurrentWezather)

        }
    }

    override suspend fun getCurrentWeather(): LiveData<out CurrentWeatherEntry> {
        return withContext(Dispatchers.IO) {
            intweatherdata()
            return@withContext dao.getCurrentWeather()
        }
    }

    private fun persistCurrentWeather(fetchWeather: CurrentWeatherResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            dao.upInsert(fetchWeather.currentweatherEntry)
        }
    }

    private suspend fun intweatherdata() {
        if (isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1))) {
            currentWeather()
        }

    }

    private suspend fun currentWeather() {
        weatherdataSource.featchWeather("Egypt")
        val language: String = Locale.getDefault().language
        Log.i("language", language)

    }

    private fun isFetchCurrentNeeded(lastFetch: ZonedDateTime): Boolean {
        val thirtyMinuteAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetch.isBefore(thirtyMinuteAgo)
    }
}
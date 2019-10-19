package com.example.testwithmvvm.data.response


import com.example.testwithmvvm.data.DataBase.CurrentWeatherEntry
import com.example.testwithmvvm.data.DataBase.Location
import com.example.testwithmvvm.data.DataBase.Request
import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentweatherEntry: CurrentWeatherEntry,
    val location: Location,
    val request: Request
)
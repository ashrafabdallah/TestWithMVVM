package com.example.testwithmvvm.data.DataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DatabaseDao {
    @Insert(onConflict =OnConflictStrategy.REPLACE)
    fun upInsert(currentweather:CurrentWeatherEntry):Long
    @Query("select * from CurrentWeather where current_id=CURRENT_ID")
    fun getCurrentWeather():LiveData<CurrentWeatherEntry>
}
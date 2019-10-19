package com.example.testwithmvvm.data.DataBase
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CurrentWeatherEntry::class],version = 1)
abstract class MyDatabase :RoomDatabase() {
    abstract fun getDaoInstance():DatabaseDao
    companion object{

        @Volatile private var instance:MyDatabase?=null
        private val Lock=Any()
        operator fun invoke(context:Context)= instance?: synchronized( Lock){
            instance?:CreateDatabase(context).also{
                instance=it
            }
        }

private fun CreateDatabase(context:Context)= Room.databaseBuilder(context,MyDatabase::class.java,"mydata")
    .build()



    }
}
package com.example.testwithmvvm.app

import android.app.Application
import com.example.testwithmvvm.data.DataBase.DatabaseDao
import com.example.testwithmvvm.data.DataBase.MyDatabase
import com.example.testwithmvvm.network.*
import com.example.testwithmvvm.repostory.Repostory
import com.example.testwithmvvm.repostory.RepostoryImpl
import com.example.testwithmvvm.ui.weather.CurrentWeatherViewModelFactory
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class Application : Application(), KodeinAware {
    override val kodein = Kodein.lazy {

        import(androidXModule(this@Application))
        bind() from singleton { MyDatabase(instance()) }
        bind() from singleton { instance<MyDatabase>().getDaoInstance() }
        // bind interface
        bind<connectivityInterceptor>() with singleton { connectivityInterceptorImpl(instance()) }
        bind() from singleton { ApiServices(instance()) }

        bind<WeatherNetworkDataSourse>() with singleton {
            WeatherNetworkDataSourseImpl(
                instance(),
                instance()
            )
        }

        bind<Repostory>() with singleton { RepostoryImpl(instance(), instance()) }
        bind() from provider { CurrentWeatherViewModelFactory(instance()) }

    }

    override fun onCreate() {
        super.onCreate()
        // to zonedDateTime
        AndroidThreeTen.init(this)
    }
}
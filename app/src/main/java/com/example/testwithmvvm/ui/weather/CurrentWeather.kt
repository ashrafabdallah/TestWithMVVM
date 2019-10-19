package com.example.testwithmvvm.ui.weather

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.example.testwithmvvm.R
import com.example.testwithmvvm.base.ScopedFragment
import com.example.testwithmvvm.network.ApiServices
import com.example.testwithmvvm.network.WeatherNetworkDataSourseImpl
import com.example.testwithmvvm.network.connectivityInterceptorImpl
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class CurrentWeather() : ScopedFragment() ,KodeinAware {
    override val kodein by closestKodein()
private val viewmodelfactory : CurrentWeatherViewModelFactory by instance()
    companion object {
        fun newInstance() = CurrentWeather()
    }

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,viewmodelfactory).get(CurrentWeatherViewModel::class.java)
        bindUI()
//        val api = ApiServices(connectivityInterceptorImpl(this.context!!))
//        val weatherdataSourse = WeatherNetworkDataSourseImpl(api, this.context!!)
//        weatherdataSourse.downloadCurrentWeather.observe(this, Observer {
//            textCurrentWeather.text = it.toString()
//        })
//        GlobalScope.launch(Dispatchers.Main) {
//            weatherdataSourse.featchWeather("Egypt")
//
//        }
    }
public fun bindUI()=launch {

    val currentweather = viewModel.weather.await()
    currentweather.observe(this@CurrentWeather, Observer {
        if(it==null)return@Observer
        textCurrentWeather.text = it.toString()
    })
}

}

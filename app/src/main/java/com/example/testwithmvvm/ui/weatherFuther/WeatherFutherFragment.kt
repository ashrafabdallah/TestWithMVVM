package com.example.testwithmvvm.ui.weatherFuther

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.testwithmvvm.R

class WeatherFutherFragment : Fragment() {

    companion object {
        fun newInstance() = WeatherFutherFragment()
    }

    private lateinit var viewModel: WeatherFutherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.weather_futher_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WeatherFutherViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

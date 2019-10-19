package com.example.testwithmvvm.ui.weatherList

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.testwithmvvm.R

class FutherListWeatherFragment : Fragment() {

    companion object {
        fun newInstance() = FutherListWeatherFragment()
    }

    private lateinit var viewModel: FutherListWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.futher_list_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FutherListWeatherViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

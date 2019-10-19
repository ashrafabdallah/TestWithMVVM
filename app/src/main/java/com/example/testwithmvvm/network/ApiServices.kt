package com.example.testwithmvvm.network


import com.example.testwithmvvm.data.response.CurrentWeatherResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val access_key="6ebf0b1071aa25c4541ae06e3422b2b4"
//http://api.weatherstack.com/current?access_key=6ebf0b1071aa25c4541ae06e3422b2b4&query=Egypt

interface ApiServices {
    @GET("current")
    fun getCurrentWeather(@Query("query")Location:String):Deferred<CurrentWeatherResponse>

    companion object
    {
        operator fun invoke(

            connectivity:connectivityInterceptor
        ):ApiServices
        {
            val requestInterceptor=Interceptor{chain ->
                val url=chain.request()
                    .url().newBuilder().addQueryParameter("access_key",access_key)
                    .build()
                val request=chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }
            val okHttpClient=OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivity)
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://api.weatherstack.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiServices::class.java)
        }
    }
}
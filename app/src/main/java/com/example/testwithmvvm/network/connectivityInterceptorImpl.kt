package com.example.testwithmvvm.network

import android.content.Context
import android.net.ConnectivityManager
import com.example.testwithmvvm.internal.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Response

class connectivityInterceptorImpl
    (context: Context) : connectivityInterceptor {
    private var appContext = context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isOnline()) {
            throw NoConnectivityException()
        }
        return chain.proceed(chain.request())
    }

    private fun isOnline(): Boolean {
        var connectivtyManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = connectivtyManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}
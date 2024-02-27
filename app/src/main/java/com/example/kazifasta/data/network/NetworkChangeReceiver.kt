package com.example.kazifasta.data.network

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat.getSystemService

class NetworkChangeReceiver(context: Context) {
    val connectivityManager = getSystemService(context, ConnectivityManager::class.java)
    val currentNetwork = connectivityManager?.activeNetwork

    val caps = connectivityManager?.getNetworkCapabilities(currentNetwork)
    val linkProperties = connectivityManager?.getLinkProperties(currentNetwork)


}


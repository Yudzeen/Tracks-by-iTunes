package com.ejavinas.trackbyitunes.common.utils.network

import android.app.Application
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

/**
 * Utility class for network connections
 */
object NetworkUtil {

    /**
     * Check if device is connected to a network
     * @param application to access [ConnectivityManager]
     * @return true if is connected to a network, false otherwise
     */
    fun hasNetworkConnection(application: Application): Boolean {
        val cm = application.getSystemService(ConnectivityManager::class.java)
        val capabilities: NetworkCapabilities = cm.getNetworkCapabilities(cm.activeNetwork)
            ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> true
            else -> false
        }
    }

}
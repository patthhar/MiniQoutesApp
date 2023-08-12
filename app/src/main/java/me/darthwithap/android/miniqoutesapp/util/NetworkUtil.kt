package me.darthwithap.android.miniqoutesapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

fun Context.isInternetConnected(): Boolean {
  val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
  val activeNetwork = connectivityManager.activeNetwork ?: return false
  val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
  return when {
    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> true
    else -> false
  }
}
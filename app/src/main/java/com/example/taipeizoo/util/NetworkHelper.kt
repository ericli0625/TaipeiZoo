package com.example.taipeizoo.util

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.core.content.getSystemService

class NetworkHelper private constructor() {

    private lateinit var context: Context

    fun initialize(context: Context) {
        this.context = context
    }

    val isConnected: Boolean
        get() {
            val info = networkInfo
            return info != null && info.isConnected
        }

    private val networkInfo: NetworkInfo?
        get() {
            val cm = context.getSystemService<ConnectivityManager>()
            return cm?.activeNetworkInfo
        }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private val helper = NetworkHelper()

        fun sharedInstance(): NetworkHelper {
            return helper
        }
    }
}

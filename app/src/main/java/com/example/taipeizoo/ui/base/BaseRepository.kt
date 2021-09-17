package com.example.taipeizoo.ui.base

import com.example.taipeizoo.util.NetworkHelper
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class BaseRepository : KoinComponent {

    private val networkHelper: NetworkHelper by inject()

    val isNetworkConnected: Boolean
        get() = networkHelper.isConnected

}
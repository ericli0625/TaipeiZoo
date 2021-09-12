package com.example.taipeizoo.network

import okhttp3.logging.HttpLoggingInterceptor

object Config {
    const val BASE_API_URL = "https://data.taipei/api/v1/dataset/"
    val INTERCEPTOR_LEVEL = HttpLoggingInterceptor.Level.BODY
}

object HttpStatusCode {
    const val INVALID = -1
}
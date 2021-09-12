package com.example.taipeizoo.model.response

import com.google.gson.annotations.SerializedName

import java.net.HttpURLConnection

data class Response<T>(
        @SerializedName("result") val data: T?,
        val httpStatusCode: Int?,
        val desc: String?,
        val type: Int?
) {
    val isSuccess: Boolean
        get() = HttpURLConnection.HTTP_MULT_CHOICE > httpStatusCode ?: 0
                && httpStatusCode ?: 0 >= HttpURLConnection.HTTP_OK

    val isNotSuccess: Boolean
        get() = !isSuccess

    val isNetworkUnavailable: Boolean
        get() = type == TYPE_UNKNOWN_HOST_EXCEPTION ||
                type == TYPE_CONNECT_EXCEPTION ||
                type == TYPE_SOCKET_TIMEOUT_EXCEPTION ||
                type == TYPE_SOCKET_EXCEPTION ||
                type == TYPE_SSL_HANDSHAKE_EXCEPTION ||
                type == TYPE_SSL_EXCEPTION ||
                type == TYPE_IO_EXCEPTION

    companion object {
        const val TYPE_NORMAL = 0
        const val TYPE_UNKNOWN_HOST_EXCEPTION = 1
        const val TYPE_CONNECT_EXCEPTION = 2
        const val TYPE_SOCKET_TIMEOUT_EXCEPTION = 3
        const val TYPE_SOCKET_EXCEPTION = 4
        const val TYPE_SSL_HANDSHAKE_EXCEPTION = 5
        const val TYPE_SSL_EXCEPTION = 6
        const val TYPE_IO_EXCEPTION = 7
        const val TYPE_HTTP_EXCEPTION = 8
        const val TYPE_DO_NOT_KNOW_HOW_TO_HANDLE_EXCEPTION = 999

        const val HTTP_STATUS_CODE_INVALID = -1
    }
}
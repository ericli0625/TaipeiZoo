package com.example.taipeizoo.network.retrofit

import com.example.taipeizoo.model.response.Response
import com.example.taipeizoo.model.response.Response.Companion.HTTP_STATUS_CODE_INVALID
import com.example.taipeizoo.model.response.Response.Companion.TYPE_CONNECT_EXCEPTION
import com.example.taipeizoo.model.response.Response.Companion.TYPE_DO_NOT_KNOW_HOW_TO_HANDLE_EXCEPTION
import com.example.taipeizoo.model.response.Response.Companion.TYPE_HTTP_EXCEPTION
import com.example.taipeizoo.model.response.Response.Companion.TYPE_IO_EXCEPTION
import com.example.taipeizoo.model.response.Response.Companion.TYPE_NORMAL
import com.example.taipeizoo.model.response.Response.Companion.TYPE_SOCKET_EXCEPTION
import com.example.taipeizoo.model.response.Response.Companion.TYPE_SOCKET_TIMEOUT_EXCEPTION
import com.example.taipeizoo.model.response.Response.Companion.TYPE_SSL_EXCEPTION
import com.example.taipeizoo.model.response.Response.Companion.TYPE_SSL_HANDSHAKE_EXCEPTION
import com.example.taipeizoo.model.response.Response.Companion.TYPE_UNKNOWN_HOST_EXCEPTION
import com.google.gson.Gson
import com.google.gson.JsonIOException
import com.google.gson.JsonSyntaxException
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import retrofit2.*
import retrofit2.adapter.rxjava2.Result
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.IOException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.net.*
import javax.net.ssl.SSLException
import javax.net.ssl.SSLHandshakeException

class RxErrorHandlingCallAdapterFactory private constructor(
        scheduler: Scheduler? = null
) : CallAdapter.Factory() {

    private val adapterFactory = if (scheduler != null) {
        RxJava2CallAdapterFactory.createWithScheduler(scheduler)
    } else {
        RxJava2CallAdapterFactory.create()
    }

    @Suppress("UNCHECKED_CAST")
    override fun get(
            returnType: Type,
            annotations: Array<Annotation>,
            retrofit: Retrofit
    ): CallAdapter<*, *>? {
        val observableType = getParameterUpperBound(0, returnType as ParameterizedType)
        val rawObservableType = getRawType(observableType)
        val isBody = rawObservableType != retrofit2.Response::class.java
                && rawObservableType != Result::class.java

        val callAdapter = adapterFactory.get(returnType, annotations, retrofit)
                as? CallAdapter<*, Any>

        return callAdapter?.let { RxCallAdapterWrapper(it, isBody) }
    }

    private class RxCallAdapterWrapper<R>(
            private val callAdapter: CallAdapter<R, *>,
            val isBody: Boolean = false
    ) : CallAdapter<R, Any> {

        override fun responseType(): Type {
            return callAdapter.responseType()
        }

        override fun adapt(call: Call<R>): Any {
            val type = callAdapter.adapt(call)

            if (!isBody || type !is Observable<*>) return type

            return type.map { res ->
                var result = res
                if (res is Response<*>) {
                    result = res.copy(
                            httpStatusCode = HttpURLConnection.HTTP_OK,
                            desc = res.desc,
                            type = TYPE_NORMAL
                    )
                }
                result
            }.onErrorResumeNext { t: Throwable ->
                if (t is retrofit2.HttpException) {
                    try {
                        val res = t.response()
                        val error = Gson().fromJson<Response<*>>(
                                res?.errorBody()?.charStream(), responseType()
                        )
                        return@onErrorResumeNext Observable.just<Response<*>>(
                                error.copy(
                                        httpStatusCode = res?.code() ?: 0,
                                        desc = error.desc,
                                        type = TYPE_NORMAL
                                )
                        )
                    } catch (e: Exception) {
                        when (e) {
                            is IllegalStateException,
                            is JsonIOException,
                            is JsonSyntaxException -> {
                                return@onErrorResumeNext Observable.just<Response<*>>(
                                        Response(null, t.code(), "", TYPE_HTTP_EXCEPTION)
                                )
                            }
                            else -> {
                                throw e
                            }
                        }
                    }
                }

                if (shouldHandleException(t)) {
                    val metaType = convertExceptionToMetaType(t)
                    return@onErrorResumeNext Observable.just<Response<*>>(
                            Response(null, HTTP_STATUS_CODE_INVALID, "", metaType)
                    )
                }

                return@onErrorResumeNext Observable.just<Response<*>>(
                        Response(null, 0, "", 0)
                )
            }.observeOn(AndroidSchedulers.mainThread())
        }

        private fun shouldHandleException(t: Throwable): Boolean {
            return t is IOException || t is JsonSyntaxException
        }

        private fun convertExceptionToMetaType(t: Throwable): Int {
            return when (t) {
                is UnknownHostException -> TYPE_UNKNOWN_HOST_EXCEPTION
                is ConnectException -> TYPE_CONNECT_EXCEPTION
                is SocketTimeoutException -> TYPE_SOCKET_TIMEOUT_EXCEPTION
                is SocketException -> TYPE_SOCKET_EXCEPTION
                is SSLHandshakeException -> TYPE_SSL_HANDSHAKE_EXCEPTION
                is SSLException -> TYPE_SSL_EXCEPTION
                is IOException -> TYPE_IO_EXCEPTION
                else -> TYPE_DO_NOT_KNOW_HOW_TO_HANDLE_EXCEPTION
            }
        }
    }

    companion object {
        fun create(scheduler: Scheduler?): CallAdapter.Factory {
            return RxErrorHandlingCallAdapterFactory(scheduler)
        }
    }
}
package com.example.taipeizoo.network.api

import com.example.taipeizoo.model.HouseInfo
import com.example.taipeizoo.model.PlaintInfo
import com.example.taipeizoo.model.response.Response
import com.example.taipeizoo.network.Config.BASE_API_URL
import com.example.taipeizoo.network.retrofit.RetrofitClient
import com.example.taipeizoo.network.retrofit.RxErrorHandlingCallAdapterFactory
import com.example.taipeizoo.network.service.ZooService
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApi : Api {

    private val zooService: ZooService by lazy {
        Retrofit.Builder()
                .baseUrl(BASE_API_URL)
                .addCallAdapterFactory(
                        RxErrorHandlingCallAdapterFactory.create(Schedulers.io())
                )
                .addConverterFactory(GsonConverterFactory.create())
                .client(RetrofitClient.getClient())
                .build()
                .create(ZooService::class.java)
    }

    override fun getHouseList(
            query: String,
            limit: Int,
            offset: Int
    ): Observable<Response<HouseInfo>> {
        return zooService.getHouseList(query, limit, offset)
    }

    override fun getPlaintList(
            query: String,
            limit: Int,
            offset: Int
    ): Observable<Response<PlaintInfo>> {
        return zooService.getPlaintList(query, limit, offset)
    }
}
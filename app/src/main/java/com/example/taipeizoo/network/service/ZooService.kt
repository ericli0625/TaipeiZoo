package com.example.taipeizoo.network.service

import com.example.taipeizoo.model.HouseInfo
import com.example.taipeizoo.model.PlaintInfo
import com.example.taipeizoo.model.response.Response
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ZooService {

    @GET("5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire")
    fun getHouseList(
            @Query("q") query: String,
            @Query("limit") limit: Int,
            @Query("offset") offset: Int
    ): Observable<Response<HouseInfo>>

    @GET("f18de02f-b6c9-47c0-8cda-50efad621c14?scope=resourceAquire")
    fun getPlaintList(
            @Query("q") query: String,
            @Query("limit") limit: Int,
            @Query("offset") offset: Int
    ): Observable<Response<PlaintInfo>>
}
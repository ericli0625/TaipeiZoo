package com.example.taipeizoo.network.api

import com.example.taipeizoo.model.HouseInfo
import com.example.taipeizoo.model.PlaintInfo
import com.example.taipeizoo.model.response.Response
import io.reactivex.Observable

interface Api {

    fun getHouseList(
            query: String = "", limit: Int = 0, offset: Int = 0
    ): Observable<Response<HouseInfo>>

    fun getPlaintList(
            query: String = "", limit: Int = 0, offset: Int = 0
    ): Observable<Response<PlaintInfo>>
}
package com.example.taipeizoo.network.api

import com.example.taipeizoo.model.HouseInfo
import com.example.taipeizoo.model.PlaintInfo
import com.example.taipeizoo.model.response.Response
import io.reactivex.Observable

class NetworkApi(private val api: Api) : Api {

    companion object {
        private val instance = NetworkApi(RetrofitApi())

        @JvmStatic
        fun sharedInstance(): NetworkApi {
            return instance
        }
    }

    override fun getHouseList(
            query: String,
            limit: Int,
            offset: Int
    ): Observable<Response<HouseInfo>> {
        return api.getHouseList(query, limit, offset)
    }

    override fun getPlaintList(
            query: String,
            limit: Int,
            offset: Int
    ): Observable<Response<PlaintInfo>> {
        return api.getPlaintList(query, limit, offset)
    }

}
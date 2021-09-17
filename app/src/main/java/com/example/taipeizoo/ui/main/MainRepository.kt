package com.example.taipeizoo.ui.main

import com.example.taipeizoo.model.HouseInfo
import com.example.taipeizoo.model.response.Response
import com.example.taipeizoo.network.api.NetworkApi
import io.reactivex.Observable

class MainRepository : IMainRepository {

    override fun fetchHouseList(): Observable<Response<HouseInfo>> {
        return NetworkApi.sharedInstance().getHouseList()
    }
}
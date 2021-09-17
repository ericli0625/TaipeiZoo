package com.example.taipeizoo.ui.house

import com.example.taipeizoo.model.PlaintInfo
import com.example.taipeizoo.model.response.Response
import com.example.taipeizoo.network.api.NetworkApi
import io.reactivex.Observable

class HouseRepository : IHouseRepository {

    override fun fetchPlantList(): Observable<Response<PlaintInfo>> {
        return NetworkApi.sharedInstance().getPlaintList()
    }
}
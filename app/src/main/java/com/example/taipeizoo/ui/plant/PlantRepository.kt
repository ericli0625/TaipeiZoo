package com.example.taipeizoo.ui.plant

import com.example.taipeizoo.model.PlaintInfo
import com.example.taipeizoo.model.response.Response
import com.example.taipeizoo.network.api.NetworkApi
import com.example.taipeizoo.ui.base.BaseRepository
import io.reactivex.Observable

class PlantRepository : BaseRepository(), IPlantRepository {

    override fun fetchPlantDetail(query: String): Observable<Response<PlaintInfo>> {
        return NetworkApi.sharedInstance().getPlaintList(query)
    }
}
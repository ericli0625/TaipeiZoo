package com.example.taipeizoo.ui.house

import com.example.taipeizoo.database.dao.HouseInfoDao
import com.example.taipeizoo.model.House
import com.example.taipeizoo.model.PlaintInfo
import com.example.taipeizoo.model.response.Response
import com.example.taipeizoo.network.api.NetworkApi
import com.example.taipeizoo.ui.base.BaseRepository
import io.reactivex.Observable
import org.koin.core.inject

class HouseRepository : BaseRepository(), IHouseRepository {

    private val houseInfoDao: HouseInfoDao by inject()

    override fun fetchPlantList(query: String): Observable<Response<PlaintInfo>> {
        return NetworkApi.sharedInstance().getPlaintList(query)
    }

    override fun getHouseDetail(id: Int): Observable<House> {
        return houseInfoDao.getHouseDetail(id)
    }
}
package com.example.taipeizoo.ui.main

import com.example.taipeizoo.database.dao.HouseInfoDao
import com.example.taipeizoo.model.House
import com.example.taipeizoo.model.HouseInfo
import com.example.taipeizoo.model.response.Response
import com.example.taipeizoo.network.api.NetworkApi
import io.reactivex.Observable
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainRepository : IMainRepository, KoinComponent {

    private val houseInfoDao: HouseInfoDao by inject()

    override fun fetchHouseList(): Observable<Response<HouseInfo>> {
        return NetworkApi.sharedInstance().getHouseList()
    }

    override suspend fun updateHouseList(data: List<House>) {
        data.map {
            houseInfoDao.insert(it)
        }
    }
}
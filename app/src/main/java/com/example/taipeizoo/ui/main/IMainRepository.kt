package com.example.taipeizoo.ui.main

import com.example.taipeizoo.model.House
import com.example.taipeizoo.model.HouseInfo
import com.example.taipeizoo.model.response.Response
import io.reactivex.Observable

interface IMainRepository {
    fun fetchHouseList(): Observable<Response<HouseInfo>>
    suspend fun updateHouseList(data: List<House>)
}
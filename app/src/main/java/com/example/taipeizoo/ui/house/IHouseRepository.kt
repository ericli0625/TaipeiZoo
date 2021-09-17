package com.example.taipeizoo.ui.house

import com.example.taipeizoo.model.PlaintInfo
import com.example.taipeizoo.model.response.Response
import io.reactivex.Observable

interface IHouseRepository {
    fun fetchPlantList(): Observable<Response<PlaintInfo>>
}
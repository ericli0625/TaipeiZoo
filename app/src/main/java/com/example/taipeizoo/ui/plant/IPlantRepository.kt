package com.example.taipeizoo.ui.plant

import com.example.taipeizoo.model.PlaintInfo
import com.example.taipeizoo.model.response.Response
import io.reactivex.Observable

interface IPlantRepository {
    fun fetchPlantDetail(query: String): Observable<Response<PlaintInfo>>
}
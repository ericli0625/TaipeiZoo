package com.example.taipeizoo.ui.plant

import com.example.taipeizoo.model.Plant
import io.reactivex.Observable

interface IPlantRepository {
    fun getPlantDetail(name: String): Observable<Plant>
}
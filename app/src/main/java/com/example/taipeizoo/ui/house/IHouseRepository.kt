package com.example.taipeizoo.ui.house

import com.example.taipeizoo.model.House
import com.example.taipeizoo.model.PlaintInfo
import com.example.taipeizoo.model.Plant
import com.example.taipeizoo.model.response.Response
import io.reactivex.Observable

interface IHouseRepository {
    fun fetchPlantList(query: String): Observable<Response<PlaintInfo>>
    fun getTargetHousePlantList(name: String): Observable<List<Plant>>
    fun getHouseDetail(id: Int): Observable<House>
    suspend fun updatePlantList(data: List<Plant>)
    fun getPlantList(): Observable<List<Plant>>
    suspend fun insertPlant(plant: Plant)
    suspend fun updatePlant(plant: Plant)
}
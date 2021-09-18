package com.example.taipeizoo.ui.house

import com.example.taipeizoo.database.dao.HouseInfoDao
import com.example.taipeizoo.database.dao.PlantInfoDao
import com.example.taipeizoo.model.House
import com.example.taipeizoo.model.PlaintInfo
import com.example.taipeizoo.model.Plant
import com.example.taipeizoo.model.response.Response
import com.example.taipeizoo.network.api.NetworkApi
import com.example.taipeizoo.ui.base.BaseRepository
import io.reactivex.Observable
import org.koin.core.inject

class HouseRepository : BaseRepository(), IHouseRepository {

    private val houseInfoDao: HouseInfoDao by inject()

    private val plantInfoDao: PlantInfoDao by inject()

    override fun fetchPlantList(query: String): Observable<Response<PlaintInfo>> {
        return NetworkApi.sharedInstance().getPlaintList(query)
    }

    override fun getTargetHousePlantList(name: String): Observable<List<Plant>> {
        return plantInfoDao.getTargetHousePlantList(name)
    }

    override fun getHouseDetail(id: Int): Observable<House> {
        return houseInfoDao.getHouseDetail(id)
    }

    override suspend fun updatePlantList(data: List<Plant>) {
        data.map {
            plantInfoDao.insert(it)
        }
    }

    override fun getPlantList(): Observable<List<Plant>> {
        return plantInfoDao.getAllPlantList()
    }

    override suspend fun insertPlant(plant: Plant) {
        plantInfoDao.insert(plant)
    }

    override suspend fun updatePlant(plant: Plant) {
        plantInfoDao.updatePlant(plant)
    }
}
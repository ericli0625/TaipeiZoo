package com.example.taipeizoo.ui.plant

import com.example.taipeizoo.database.dao.PlantInfoDao
import com.example.taipeizoo.model.Plant
import com.example.taipeizoo.ui.base.BaseRepository
import io.reactivex.Observable
import org.koin.core.inject

class PlantRepository : BaseRepository(), IPlantRepository {

    private val plantInfoDao: PlantInfoDao by inject()

    override fun getPlantDetail(name: String): Observable<Plant> {
        return plantInfoDao.getPlantDetail(name)
    }
}
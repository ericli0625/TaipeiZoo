package com.example.taipeizoo.database.dao

import androidx.room.*
import com.example.taipeizoo.model.Plant
import io.reactivex.Observable

@Dao
interface PlantInfoDao {

    @Query("SELECT * FROM plantList")
    fun getAllPlantList(): Observable<List<Plant>>

    @Query("SELECT * FROM plantList WHERE nameC LIKE :name")
    fun getPlantDetail(name: String): Observable<Plant>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(plant: Plant)

    @Delete
    suspend fun delete(plant: Plant)
}
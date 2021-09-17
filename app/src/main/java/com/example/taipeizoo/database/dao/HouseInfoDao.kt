package com.example.taipeizoo.database.dao

import androidx.room.*
import com.example.taipeizoo.model.House
import io.reactivex.Observable

@Dao
interface HouseInfoDao {

    @Query("SELECT * FROM houseList")
    fun getAllHouseList(): Observable<List<House>>

    @Query("SELECT * FROM houseList WHERE id LIKE :id")
    fun getHouseDetail(id: Int): Observable<House>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(house: House)

    @Delete
    suspend fun delete(house: House)
}
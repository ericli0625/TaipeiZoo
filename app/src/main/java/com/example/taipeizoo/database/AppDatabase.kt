package com.example.taipeizoo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.taipeizoo.database.dao.HouseInfoDao
import com.example.taipeizoo.model.House

@Database(entities = [House::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun houseInfo(): HouseInfoDao

    companion object {

        const val DATABASE_NAME = "taipei_zoo.db"

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                    .build()
        }
    }
}
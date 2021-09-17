package com.example.taipeizoo.koin

import com.example.taipeizoo.database.AppDatabase
import com.example.taipeizoo.ui.house.HouseRepository
import com.example.taipeizoo.ui.main.MainRepository
import com.example.taipeizoo.ui.plant.PlantRepository
import com.example.taipeizoo.util.NetworkHelper
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule: Module = module {

    factory { MainRepository() }
    factory { HouseRepository() }
    factory { PlantRepository() }

    single { NetworkHelper.sharedInstance().apply { initialize(androidContext()) } }
    single { AppDatabase.getInstance(get()).houseInfo() }
    single { AppDatabase.getInstance(get()).plantInfo() }
}

val appModule = listOf(repositoryModule)
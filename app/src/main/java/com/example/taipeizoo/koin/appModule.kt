package com.example.taipeizoo.koin

import com.example.taipeizoo.ui.house.HouseRepository
import com.example.taipeizoo.ui.main.MainRepository
import com.example.taipeizoo.ui.plant.PlantRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule: Module = module {

    factory { MainRepository() }
    factory { HouseRepository() }
    factory { PlantRepository() }

}

val appModule = listOf(repositoryModule)
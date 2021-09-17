package com.example.taipeizoo.ui.main

import com.example.taipeizoo.model.HouseInfo

open class MainContract {

    interface IMainPresenter {
        fun fetchHouseList()
    }

    interface IMainView {
        fun updateHouseListResult(data: HouseInfo)
    }
}
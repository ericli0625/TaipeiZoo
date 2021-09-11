package com.example.taipeizoo.ui.house

class HouseContract {

    interface IHousePresenter {
        fun fetchPlantList()
    }

    interface IHouseView {
        fun updatePlantListResult()
    }
}
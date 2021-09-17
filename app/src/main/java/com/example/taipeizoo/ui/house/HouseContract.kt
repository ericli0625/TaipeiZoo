package com.example.taipeizoo.ui.house

import com.example.taipeizoo.model.House
import com.example.taipeizoo.model.Plant

class HouseContract {

    interface IHousePresenter {
        fun viewReady(id: Int, name: String)
    }

    interface IHouseView {
        fun updatePlantListResult(plants: List<Plant>, isStopShimmer: Boolean = false)
        fun updateHouse(house: House)
        fun showErrorSnackBar()
    }
}
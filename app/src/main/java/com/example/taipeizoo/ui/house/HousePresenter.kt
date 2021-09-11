package com.example.taipeizoo.ui.house

import com.example.taipeizoo.ui.base.BasePresenter

class HousePresenter(
        private val view: HouseContract.IHouseView
) : BasePresenter(), HouseContract.IHousePresenter {

    override fun fetchPlantList() {
        view.updatePlantListResult()
    }

}
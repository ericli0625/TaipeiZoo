package com.example.taipeizoo.ui.house

import com.example.taipeizoo.ui.base.BasePresenter
import org.koin.core.KoinComponent
import org.koin.core.inject

class HousePresenter(
        private val view: HouseContract.IHouseView
) : BasePresenter(), HouseContract.IHousePresenter, KoinComponent {

    private val repository: IHouseRepository by inject<HouseRepository>()

    override fun fetchPlantList() {
        view.updatePlantListResult()
    }

}
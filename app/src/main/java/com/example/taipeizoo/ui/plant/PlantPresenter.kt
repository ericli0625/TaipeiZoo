package com.example.taipeizoo.ui.plant

import com.example.taipeizoo.ui.base.BasePresenter

class PlantPresenter(
        private val view: PlantContract.IPlantView
) : BasePresenter(), PlantContract.IPlantPresenter {

    override fun fetchPlantDetail() {
        view.updatePlantDetailResult()
    }

}
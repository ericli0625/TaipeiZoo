package com.example.taipeizoo.ui.plant

import com.example.taipeizoo.model.Plant

class PlantContract {

    interface IPlantPresenter {
        fun fetchPlantDetail(query: String)
    }

    interface IPlantView {
        fun updatePlantDetailResult(plant: Plant)
        fun showErrorSnackBar()
    }
}
